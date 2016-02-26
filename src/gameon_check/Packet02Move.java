package gameon_check;

public class Packet02Move extends Packet {

	private String username;
	private int x, y;
	private int keyCode;
	private long health;

	public Packet02Move(byte[] data) {
		super(02);
		String st[] = readData(data).split(",");
		this.username = st[0];
		this.x = Integer.parseInt(st[1]);
		this.y = Integer.parseInt(st[2]);
		this.keyCode = Integer.parseInt(st[3]);
		this.health = (long)Integer.parseInt(st[4]);

	}

	public Packet02Move(String username, float f, float g, int keyCode,long health) {
		super(02);
		this.username = username;
		this.x = (int)f;
		this.y = (int)g;
		this.keyCode = keyCode;
		this.health = health;

	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendToAll(getData());
	}

	@Override
	public byte[] getData() {
		return ("02" + this.username + "," + this.x + "," + this.y + "," + keyCode+","+this.health)
				.getBytes();
	}

	public String getUsername() {
		return username;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public long getHealth() {
		return health;
	}
	

}
