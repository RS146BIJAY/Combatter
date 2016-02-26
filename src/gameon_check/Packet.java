package gameon_check;

public abstract class Packet {
	public static enum PacketTypes{
		INVALID(-1),LOGIN(00),DISCONNECT(01),MOVE(02);
		
		private int id;
		
		private PacketTypes(int id)
		{
			this.id = id;
		}
		
		public int getId()
		{
			return id;
		}
	}
	public byte id;
	public Packet(int id)
	{
		this.id=(byte)id;
	}
	
	public abstract byte[] getData();
	
	public abstract void writeData(GameClient client);
	
	public abstract void writeData(GameServer server);
	
	public String readData(byte ar[])
	{
		String msg = new String(ar).trim().substring(2);
		return msg;
	}
	
	public static PacketTypes lookupPacket(int id)
	{
		for(PacketTypes p :PacketTypes.values())
		{
			if(p.getId()==id)
			{
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
	
	public static PacketTypes lookupPacket(String id)
	{
		try
		{
			return lookupPacket(Integer.parseInt(id));
		}catch(NumberFormatException e)
		{
			return PacketTypes.INVALID;
		}
	}
}
