package de.sfnkassel.javaBeam.util;

import javafx.scene.paint.Color;

public class ByteConversions {
	
	public static Byte[] fromInt(int in){
		Byte[] out = new Byte[4];
		out[0] = (byte) ((in & 0xff000000) >>> 24);
		out[1] = (byte) ((in & 0x00ff0000) >>> 16);
		out[2] = (byte) ((in & 0x0000ff00) >>> 8);
		out[3] = (byte) (in & 0x000000ff);
		return out;
	}
	
	public static int intFromByteArray(Byte[] in){
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
	
	public static char charFromByteArray(Byte[] in){
		char out = 0;
		out |= (((char)((byte)in[0])) << 8) & 0xff00;
		out |= ((char)((byte)in[1])) & 0x00ff;
		return out;
	}
	
	public static byte[] stringToByteArray(String s) {
		
		byte[] out = new byte[s.length() * 2];
		
		for(int i = 0; i < s.length(); i++){
			out[i*2] = fromChar(s.charAt(i))[0];
			out[(i*2) + 1] = fromChar(s.charAt(i))[1];
		}
		
		return out;
	}
	
	public static int colorPartFromByte(byte in) {
		System.err.println(in < 0 ? 127 - in : in);
		return in < 0 ? 127 - in : in;
	}
	
	public static Color colorFromByteArray(Byte[] in, int offset) {
		return new Color(((float)colorPartFromByte(in[offset + 0]))/255,
						((float)colorPartFromByte(in[offset + 1]))/255,
						((float)colorPartFromByte(in[offset + 2]))/255, 0);
	}
}
