package gameon_check;

import gameon_check.Packet.PacketTypes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameClient extends Thread {

	Tester test;
	DatagramSocket connection;
	InetAddress ip;

	public GameClient(Tester test, String ipAddress) {
		this.test = test;
		try {
			this.connection = new DatagramSocket();
			this.ip = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
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
			// System.out.println("Server > "+new
			// String(packet.getData()).trim());
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

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
					+ " has joined");
			PlayerMP player = new PlayerMP(500, 500,
					((Packet00Login) packet).getUsername(), address, port, -1);
			test.addPlayer(player);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("[" + address.getHostAddress() + "." + port
					+ "] " + ((Packet01Disconnect) packet).getUsername()
					+ " has left the world .....");
			test.removePlayer(((Packet01Disconnect) packet).getUsername());
			break;
		case MOVE:
			packet = new Packet02Move(data);
			this.handleMovement((Packet02Move) packet);
		}

	}

	public void handleMovement(Packet02Move packet) {
		this.test.playerMovement(((Packet02Move) packet).getUsername(),
				((Packet02Move) packet).getX(), ((Packet02Move) packet).getY(),
				((Packet02Move) packet).getKeyCode(),((Packet02Move) packet).getHealth());
	}

	public void sendData(byte[] ar) {
		DatagramPacket p = new DatagramPacket(ar, ar.length, ip, 1331);
		try {
			connection.send(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
