package gameon_check;

import java.awt.Image;

public class Sprites {
	private Animation a;
	private float x=0;
	private float y = 400;
	private float vx;
	private float vy;
	private String name;

	public Sprites(Animation a,String name) {
		this.a = a;
		this.name=name;
	}

	public void update(long timePassed) {
		x += vx * timePassed;
		y += vy * timePassed;
		a.update(timePassed);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return a.getImage().getWidth(null);
	}

	public int getHeight() {
		return a.getImage().getHeight(null);
	}

	public float getVelocityX() {
		return vx;
	}

	public float getVelocityY() {
		return vy;
	}

	public void setVelocityX(float vx) {
		this.vx = vx;
	}

	public void setVelocityY(float vy) {
		this.vy = vy;
	}

	public Image getImage() {
		return a.getImage();
	}
	public void start()
	{
		a.start();
	}
	public String info()
	{
		return a.info()+name;
	}

	public long curAnimationDefence() {
		// TODO Auto-generated method stub
		return a.curAnimationDefence();
	}

	public long curAnimationPower() {
		// TODO Auto-generated method stub
		return a.curAnimationPower();
	}
}
