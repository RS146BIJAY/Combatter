package gameon_check;

import java.util.List;

public class Health {

	Player p1;
	Player p2;

	public Health(List<Player> p) {
		this.p1 = p.get(0);
		this.p2 = p.get(1);
	}

	public void checkHealth() {
		if (p1.health <= 0) {
			p1.exsists = false;
		}
		if (p2.health <= 0) {
			p2.exsists = false;
		}
	}

	public void decreaseHealth() {
		if (p1.exsists && p2.exsists) {
			long power1 = p1.curAnimationPower();
			long defence1 = p1.curAnimationDefence();
			long power2 = p2.curAnimationPower();
			long defence2 = p2.curAnimationDefence();

			long dec = power1 + defence1 - power2 - defence2;

			if (dec > 0) {
				p2.decHealth(dec);
			} else {
				p1.decHealth(dec);
			}
			checkHealth();
		}
	}
}