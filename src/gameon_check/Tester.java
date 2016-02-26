package gameon_check;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tester extends JPanel implements Runnable, KeyListener,
		WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Player class controls the actions of 1st player Player2 class controls
	 * the actions of 2nd player Health class controls increment or decrement in
	 * the health of the players
	 */
	JFrame window;
	Player p1;
	Health health;
	Image img, end;
	long startingTime, cumTime, timePassed;
	Image bg;
	private boolean running;
	List<Player> p;
	GameClient client;
	Player pq;
	GameServer server;
	String use = "";

	public Tester() {
		running = true;

		window = new JFrame();
		window.add(this);
		window.setSize(800, 600);
		window.addKeyListener(this);
		// window.addMouseListener(this);
		window.addWindowListener(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		p1 = new PlayerMP(500, 0.0f, 1, JOptionPane.showInputDialog(window,
				"Please enter a username"), null, -1, 1);
		p = new ArrayList<Player>();
		p.add(p1);

		if (JOptionPane.showConfirmDialog(this,
				"Do you want to run the server?") == 0) {
			server = new GameServer(this);
			server.start();
		}
		String st = JOptionPane.showInputDialog(window,
				"Enter the name of server to be connected");
		try {
			client = new GameClient(this, InetAddress.getByName(st)
					.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		client.start();

	}

	// utility function to load images

	public void loadImage() {
		img = new ImageIcon(getClass().getResource("/f05.gif")).getImage();
		end = new ImageIcon(getClass().getResource("/end.png")).getImage();
		bg = new ImageIcon(getClass().getResource("/pit.png")).getImage();
		Iterator<Player> it = p.iterator();
		while (it.hasNext()) {
			it.next().loadImage();
		}
		// client.sendData("Ping".getBytes());
		Packet00Login packet = new Packet00Login(p1.getUserName());
		if (server != null) {
			server.addConection((PlayerMP) p1, packet);
		}
		packet.writeData(client);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, null);
		if (health != null)
			health.checkHealth();
		if (p != null) {
			g.setColor(Color.WHITE);
			// g.drawString(p1.getUserName() + p1.exsists, 200, 400);
			if (p.size() > 1) {
				g.drawString(use, 400, 400);
				g.drawString(p.get(1).getUserName() + p.get(1).exsists, 400,
						400);
			}
			Iterator<Player> it = p.iterator();
			try {
				while (it.hasNext()) {
					pq = it.next();
					if (pq.exsists) {
						pq.draw(g);
						g.drawString("Number of players " + p.size(), 200, 150);
					} else {

						g.drawImage(img, Math.round(pq.getX()),
								Math.round(pq.getY()), null);
						g.drawImage(end, 400, 300, null);
						// g.drawRect(280, 470, 400, 40);
						// g.drawString("TRY Again ?", 300, 500);
					}
				}

			} catch (Exception e) {

			}
		}
	}

	public static void main(String args[]) {
		Tester obj = new Tester();

		Thread th = new Thread(obj);
		th.start();
	}

	public void run() {
		loadImage();
		movieLoop();
	}

	public void movieLoop() {
		long startingTime = System.currentTimeMillis();
		cumTime = startingTime;
		while (running) {
			timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			Iterator<Player> it = p.iterator();
			while (it.hasNext())
				it.next().update(timePassed, getWidth(), getHeight());

			if (p.size() > 1 && p.get(0).getGame() != null
					&& p.get(1).getGame() != null
					&& Math.abs(p.get(0).getX() - p.get(1).getX()) < 80) {
				health.decreaseHealth();
			}

			repaint();

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void addPlayer(PlayerMP player) {
		p.add(player);
		player.loadImage();
		health = new Health(p);
	}

	public long getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(long timePassed) {
		this.timePassed = timePassed;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		Game game = p.get(0).getGame();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			running = false;
		} else if (keyCode == KeyEvent.VK_1 && p1.exsists) {
			game.setCurrent(keyCode);
			Packet02Move packet = new Packet02Move(p1.getUserName(),
					(int) p1.getX(), (int) p1.getY(), keyCode, p1.getHealth());
			packet.writeData(client);

		} else if (keyCode == KeyEvent.VK_2 && p1.exsists) {
			game.setCurrent(keyCode);
			Packet02Move packet = new Packet02Move(p1.getUserName(),
					(int) p1.getX(), (int) p1.getY(), keyCode, p1.getHealth());
			packet.writeData(client);
		} else if (keyCode == KeyEvent.VK_3 && p1.exsists) {
			game.setCurrent(keyCode);
			Packet02Move packet = new Packet02Move(p1.getUserName(),
					(int) p1.getX(), (int) p1.getY(), keyCode, p1.getHealth());
			packet.writeData(client);
		} else {
			arg0.consume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (p1.exsists && p1.getGame() != null) {
			p1.getGame().setCurrent(KeyEvent.VK_0);
			Packet02Move packet = new Packet02Move(p1.getUserName(),
					(int) p1.getX(), (int) p1.getY(), KeyEvent.VK_0,
					p1.getHealth());
			packet.writeData(client);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		arg0.consume();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent event) {
		Packet01Disconnect packet = new Packet01Disconnect(p1.getUserName());
		packet.writeData(client);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

	public void removePlayer(String username) {
		Iterator<Player> it = p.iterator();
		Player pq;
		while (it.hasNext()) {
			pq = it.next();
			if (pq instanceof PlayerMP
					&& ((PlayerMP) pq).getUserName().equalsIgnoreCase(username)) {
				it.remove();
				break;
			}
		}
	}

	public void playerMovement(String username, int x, int y, int keyCode,
			long health) {
		if (p.get(1).getGame() != null)
			p.get(1).getGame().setCurrent(keyCode);
		p.get(1).setHealth(health);
		use = username;
	}
}
