package de.sfnkassel.pixelbeamer.server.util;

public class ByteConversions {
	
	public static byte[] fromInt(int in){
		byte[] out = new byte[4];
		out[0] = (byte) ((in & 0xff000000) >>> 24);
		out[1] = (byte) ((in & 0x00ff0000) >>> 16);
		out[2] = (byte) ((in & 0x0000ff00) >>> 8);
		out[3] = (byte) (in & 0x000000ff);
		return out;
	}
	
	public static int intFromByteArray(byte[] in){
		int out = 0;
		out |= (((int)in[0]) << 24) & 0xff000000;
		out |= (((int)in[1]) << 16) & 0x00ff0000;
		out |= (((int)in[2]) << 8) & 0x0000ff00;
		out |= ((int)in[3]) & 0x000000ff;
		return out;
	}
	
	public static byte[] fromChar(char in){
		byte[] out = new byte[2];
		out[0] = (byte) ((in & 0xff00) >>> 8);
		out[1] = (byte) (in & 0x00ff);
		return out;
	}
	
	public static char charFromByteArray(byte[] in){
		char out = 0;
		out |= (((char)in[0]) << 8) & 0xff00;
		out |= ((char)in[1]) & 0x00ff;
		return out;
	}
}
