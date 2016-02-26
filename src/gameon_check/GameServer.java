package gameon_check;

import gameon_check.Packet.PacketTypes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread {
	Tester test;
	DatagramSocket connection;
	List<PlayerMP> connectedPlayer = new ArrayList<PlayerMP>();

	public GameServer(Tester test) {
		this.test = test;
		try {
			this.connection = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				connection.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			/*
			 * String msg =new String(packet.getData()).trim();
			 * if(msg.equals("Ping")){
			 * System.out.println("CLIENT ["+packet.getAddress
			 * ().getHostAddress()+ ":"+packet.getPort()+"] >"+msg);
			 * sendData("Pong".getBytes(),packet.getAddress(),packet.getPort());
			 * }
			 */

		}
	}

	private void parsePacket(byte[] data, InetAddress address, int port) {

		String msg = new String(data).trim();
		PacketTypes types = Packet.lookupPacket(msg.substring(0, 2));
		Packet packet = null;

		switch (types) {
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println("[" + address.getHostAddress() + "." + port
					+ "] " + ((Packet00Login) packet).getUsername()
					+ " has connected");
			PlayerMP player = new PlayerMP(500, 500,
					((Packet00Login) packet).getUsername(), address, port,-1);
			this.addConection(player, ((Packet00Login) packet));
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + "." + port
					+ "] " + ((Packet01Disconnect) packet).getUsername()
					+ " has left");
			this.removeConection(((Packet01Disconnect) packet));
			break;
		case MOVE:
			packet = new Packet02Move(data);
			this.handleMovements((Packet02Move) packet);
			break;
		}

	}

	public void handleMovements(Packet02Move packet) {
		if (packet.getUsername() != null) {
			int index = getPlayerMPIndex(packet.getUsername());
			PlayerMP player = connectedPlayer.get(index);
			if (player.getGame() != null) {
				player.getGame().setCurrent(packet.getKeyCode());
			}
			player.setHealth(packet.getHealth());
			packet.writeData(this);
		}
	}

	public void addConection(PlayerMP player, Packet00Login packet) {
		boolean alreadyConnected = false;
		for (PlayerMP p : connectedPlayer) {
			if (player.getUserName().equalsIgnoreCase(p.getUserName())) {
				if (p.ip == null) {
					p.ip = player.ip;
				}
				if (p.port == -1) {
					p.port = player.port;
				}
				alreadyConnected = true;
			} else {
				sendData(packet.getData(), p.ip, p.port);

				packet = new Packet00Login(p.getUserName());
				sendData(packet.getData(), player.ip, player.port);
			}
		}
		if (!alreadyConnected)
			this.connectedPlayer.add(player);
	}

	public void removeConection(Packet01Disconnect packet) {
		this.connectedPlayer.remove(getPlayerMPIndex(packet.getUsername()));
		packet.writeData(this);
	}

	public PlayerMP getPlayerMP(String userName) {
		for (PlayerMP player : connectedPlayer) {
			if (player.getUserName().equalsIgnoreCase(userName))
				return player;
		}
		return null;
	}

	public int getPlayerMPIndex(String userName) {
		int index = 0;
		for (PlayerMP player : connectedPlayer) {
			if (player.getUserName().equalsIgnoreCase(userName))
				break;
			++index;
		}
		return index;
	}

	public void sendData(byte[] ar, InetAddress ip, int port) {
		DatagramPacket p = new DatagramPacket(ar, ar.length, ip, port);
		try {
			connection.send(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendToAll(byte[] data) {
		for (PlayerMP p : connectedPlayer) {
			if(!p.getUserName().equalsIgnoreCase(new String(data).substring(2).trim().split(",")[0]))
			sendData(data, p.ip, p.port);
		}
	}
}
