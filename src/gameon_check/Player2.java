package gameon_check;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player2 {
	private int x, y;
	private Sprites s;
	private Animation animate;
	public boolean exsists;
	public long health,defHealth;
	private Image logo1;

	public Player2() {
		x = 600;
		y = 400;
		health = 1000;
		exsists = true;
		defHealth=health;

		animate = new Animation(20, 30);
		try {
			logo1 = ImageIO.read(new File(
					"D:/work/Sprites/Dragon/dragonlogo.png"));

			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/1.png")), 250);
			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/2.png")), 250);
			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/3.png")), 250);
			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/4.png")), 250);
			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/5.png")), 250);
			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/7.png")), 250);
			animate.addScene(
					ImageIO.read(new File("D:/work/Sprites/Dragon/8.png")), 250);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = new Sprites(animate, "");
		s.setVelocityX(0);
		s.setVelocityY(0);

	}

	public void update(long timePassed, Player p) {
		if (exsists) {
			s.update(timePassed);
			checkForCollison(p);
		}
	}

	private void checkForCollison(Player p) {
		if (p.getX() > x) {
			p.setX(x - 10);
		}
	}

	public void draw(Graphics g) {
		g.drawImage(logo1, 600, 0, null);
		g.drawString(health + " ", 700, 150);

		if (exsists)
			g.drawImage(s.getImage(), x, y, null);
	}

	public long curAnimationPower() {
		// TODO Auto-generated method stub
		return s.curAnimationPower();
	}

	public long curAnimationDefence() {
		// TODO Auto-generated method stub
		return s.curAnimationDefence();
	}

	public void decHealth(long dec) {
		health -= Math.abs(dec);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void restart()
	{
		exsists=true;
		health=defHealth;
		s.start();
	}
}