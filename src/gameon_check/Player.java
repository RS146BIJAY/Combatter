package gameon_check;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	/*
	 * Game class sets the the sprite to be drawn on screen based on key pressed
	 * Sprites class controls the sprites of the game Animation class handles
	 * adding or removing of one scene from the game
	 */

	private Game game;
	private Sprites s;
	private Animation animate;
	private List<Sprites> sList;
	public long health, defHealth;
	public boolean exsists;
	Image img, end, logo2;
	float x;
	int input;
	String name;
	int kfactor;

	public Player(long health, float x, int input, String name, int kfactor) {
		this.health = health;
		this.defHealth = health;
		exsists = true;
		this.x = x;
		input = this.input;
		this.name = name;
		this.kfactor = kfactor;
	}

	public String getUserName() {
		return name;
	}

	public void loadImage() {
		if (kfactor == 1) {
			logo2 = new ImageIcon(getClass().getResource("/logo.png"))
					.getImage();

			sList = new ArrayList<Sprites>();

			animate = new Animation(10, 10);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/punching1/01.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/punching1/02.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/punching1/03.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/punching1/04.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/punching1/05.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/punching1/06.gif")).getImage(),
					100);
			s = new Sprites(animate, "sprites 2");
			s.setVelocityX(0);
			s.setVelocityY(0);
			sList.add(s);

			animate = new Animation(10, 30);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/FightingStance/a01.gif"))
							.getImage(), 100);

			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/FightingStance/a02.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/FightingStance/a03.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/FightingStance/a04.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/FightingStance/a05.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/FightingStance/a06.gif"))
							.getImage(), 100);

			s = new Sprites(animate, "sprites 1");
			s.setVelocityX(0);
			s.setVelocityY(0);
			sList.add(s);

			animate = new Animation(10, 80);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/Finishers/01.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/Finishers/02.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/Finishers/03.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/Finishers/04.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/Finishers/05.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/Finishers/06.gif")).getImage(),
					100);

			s = new Sprites(animate, "sprites 2");
			s.setVelocityX(0);
			s.setVelocityY(0);
			sList.add(s);

			animate = new Animation(50, 50);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f01.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f02.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f03.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f04.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f05.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f06.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f07.gif")).getImage(),
					100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart/jumping3/f08.gif")).getImage(),
					100);

			s = new Sprites(animate, "sprites 2");
			s.setVelocityX(kfactor * 0.25f);
			s.setVelocityY(0);
			sList.add(s);
			game = new Game(sList);
			setX(x);
		} else {
			logo2 = new ImageIcon(getClass().getResource("/logo.png"))
					.getImage();

			sList = new ArrayList<Sprites>();

			animate = new Animation(10, 10);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/punching1/01.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/punching1/02.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/punching1/03.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/punching1/04.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/punching1/05.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/punching1/06.gif"))
							.getImage(), 100);
			s = new Sprites(animate, "sprites 2");
			s.setVelocityX(0);
			s.setVelocityY(0);
			sList.add(s);

			animate = new Animation(10, 30);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/FightingStance/a01.gif"))
							.getImage(), 100);

			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/FightingStance/a02.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/FightingStance/a03.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/FightingStance/a04.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/FightingStance/a05.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/FightingStance/a06.gif"))
							.getImage(), 100);

			s = new Sprites(animate, "sprites 1");
			s.setVelocityX(0);
			s.setVelocityY(0);
			sList.add(s);

			animate = new Animation(10, 80);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/Finishers/01.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/Finishers/02.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/Finishers/03.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/Finishers/04.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/Finishers/05.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/Finishers/06.gif"))
							.getImage(), 100);

			s = new Sprites(animate, "sprites 2");
			s.setVelocityX(0);
			s.setVelocityY(0);
			sList.add(s);

			animate = new Animation(50, 50);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f01.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f02.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f03.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f04.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f05.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f06.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f07.gif"))
							.getImage(), 100);
			animate.addScene(
					new ImageIcon(getClass().getResource(
							"/Mortal_combart_mirrior/jumping3/f08.gif"))
							.getImage(), 100);

			s = new Sprites(animate, "sprites 2");
			s.setVelocityX(kfactor * 0.25f);
			s.setVelocityY(0);
			sList.add(s);
			game = new Game(sList);
			setX(x);
		}
	}

	public void draw(Graphics g) {
		if (kfactor == 1) {
			g.drawImage(logo2, 0, 0, null);
			g.setColor(Color.WHITE);
			g.drawString(health + " ", 100, 150);
		} else {
			g.drawImage(logo2, 400, 0, null);
			g.setColor(Color.WHITE);
			g.drawString(health + " ", 500, 150);
		}
		if (exsists && game != null)
			g.drawImage(game.getImage(), Math.round(game.getX()),
					Math.round(game.getY()), null);
		else if (game == null)
			g.drawString("Game is null " + name, 300, 150);
	}

	public void update(long timePassed, int width, int height) {
		if (exsists && game != null)
			game.update(timePassed, width, height);
	}

	public Game getGame() {
		return game;
	}

	public float getX() {
		return game.getX();
	}

	public void setX(float x) {
		game.setX(x);
	}

	public float getY() {
		return game.getY();
	}

	public void setY(float y) {
		game.setX(y);
	}

	public long curAnimationDefence() {
		return game.curAnimationDefence();
	}

	public long curAnimationPower() {
		return game.curAnimationPower();
	}

	public void decHealth(long dec) {
		health -= Math.abs(dec);

	}

	public void restart() {
		exsists = true;
		health = defHealth;
		game.start();
		setX(0);
	}

	public void setHealth(long health) {
		this.health = health;
	}

	public long getHealth() {
		return health;
	}

}
