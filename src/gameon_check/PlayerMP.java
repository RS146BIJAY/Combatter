package gameon_check;

import java.net.InetAddress;

public class PlayerMP extends Player{
	InetAddress ip;
	int port;

	public PlayerMP(long health, float x, int input, String name,InetAddress ip,int port,int kfactor) {
		super(health, x, input, name,kfactor);
		this.ip=ip;
		this.port = port;
	}
	
	public PlayerMP(long health, float x, String name,InetAddress ip,int port,int kfactor) {
		super(health, x, 0, name,kfactor);
		this.port = port;
		this.ip = ip;
	}
}
