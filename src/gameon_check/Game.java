package gameon_check;


import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;

public class Game {
	List<Sprites> aList;
	Sprites cAnimate;
	int prevCode;

	public Game(List<Sprites> aList) {
		this.aList = aList;
		cAnimate = aList.get(1);
		prevCode = KeyEvent.VK_0;
	}

	public void update(long timePassed, int w, int h) {
		if (cAnimate.getX() < 0) {
			cAnimate.setX(w - cAnimate.getWidth());
		} else if (cAnimate.getX() + cAnimate.getWidth() >= w)
			cAnimate.setX(0);
		cAnimate.update(timePassed);
	}

	public void setCurrent(int keyCode) {
		float x = cAnimate.getX();
		if (keyCode == KeyEvent.VK_0) {
			cAnimate = aList.get(1);
			cAnimate.start();
			cAnimate.setX(x);
			prevCode = KeyEvent.VK_0;
		} else if (keyCode == KeyEvent.VK_1 && prevCode == KeyEvent.VK_0) {
			cAnimate = aList.get(0);
			cAnimate.start();
			cAnimate.setX(x);
			prevCode = KeyEvent.VK_1;
		} else if (keyCode == KeyEvent.VK_2 && prevCode == KeyEvent.VK_0) {
			cAnimate = aList.get(2);
			cAnimate.start();
			cAnimate.setX(x);
			prevCode = KeyEvent.VK_2;
		} else if (keyCode == KeyEvent.VK_3 && prevCode == KeyEvent.VK_0) {
			cAnimate = aList.get(3);
			cAnimate.start();
			cAnimate.setX(x);
			prevCode = KeyEvent.VK_3;
		}
	}

	public Image getImage() {
		return cAnimate.getImage();
	}

	public float getX() {
		return cAnimate.getX();
	}

	public float getY() {
		return cAnimate.getY();
	}

	public String info() {
		return cAnimate.info();
	}
	public void setX(float x)
	{
		cAnimate.setX(x);
	}
	public void setY(float y)
	{
		cAnimate.setY(y);
	}

	public long curAnimationDefence() {
		// TODO Auto-generated method stub
		return cAnimate.curAnimationDefence();
	}

	public long curAnimationPower() {
		// TODO Auto-generated method stub
		return cAnimate.curAnimationPower();
	}
	
	public void start()
	{
		cAnimate = aList.get(1);
		prevCode = KeyEvent.VK_0;
		cAnimate.start();
	}
}
