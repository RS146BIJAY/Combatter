package gameon_check;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList<OneScene> scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	private long timePassed;
	private long defence;
	private long power;
	int co;

	public Animation(long defence, long power) {
		scenes = new ArrayList<OneScene>();
		totalTime = 0;
		this.defence = defence;
		this.power = power;
		co = 0;
		start();
	}

	public synchronized void addScene(Image i, long t) {
		totalTime += t;
		++co;
		scenes.add(new OneScene(i, totalTime, co));
	}

	public synchronized void start() {
		movieTime = 0;
		sceneIndex = 0;
	}

	public synchronized void update(long timePassed) {
		this.timePassed = timePassed;
		if (scenes.size() > 1) {
			movieTime += timePassed;
			if (movieTime >= totalTime) {
				movieTime = 0;
				sceneIndex = 0;
			}
			while (movieTime > getScene(sceneIndex).endTime) {
				++sceneIndex;
			}
		}
	}

	public synchronized Image getImage() {
		if (scenes.size() == 0) {
			return null;
		} else {
			return getScene(sceneIndex).i;
		}
	}

	private OneScene getScene(int x) {
		return scenes.get(x);
	}

	private class OneScene {
		Image i;
		long endTime;

		public OneScene(Image i, long endTime, long in) {
			this.i = i;
			this.endTime = endTime;
		}
	}

	public String info() {
		return sceneIndex + " " + movieTime + " " + totalTime + " "
				+ timePassed + " ";
	}

	public long curAnimationDefence() {
		// TODO Auto-generated method stub
		return defence;
	}

	public long curAnimationPower() {
		// TODO Auto-generated method stub
		return power;
	}
}