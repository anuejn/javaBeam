package de.sfnkassel.javaBeam.client;

import java.io.IOException;
import java.net.Socket;

import de.sfnkassel.javaBeam.server.util.ByteConversions;

public class JavabeamClient {
	private String ip;

	public JavabeamClient(String ip) {
		this.ip = ip;
	}
	
	public static void main(String[] args) throws IOException {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		beamer.drawText(10, 30, 0, 0, 0, 127, "olol");
	}

	public void drawText(int x, int y, int r, int g, int b, int fontsize, String text) throws IOException {
		byte[] byteText = ByteConversions.stringToByteArray(text);
		byte[] out = new byte[14 + byteText.length];
		out[0] = CMD_DRAW_TEXT;
		out[1] = (byte) r;
		out[2] = (byte) g;
		out[3] = (byte) b;
		out[4] = ByteConversions.fromInt(x)[0];
		out[5] = ByteConversions.fromInt(x)[1];
		out[6] = ByteConversions.fromInt(x)[2];
		out[7] = ByteConversions.fromInt(x)[3];
		out[8] = ByteConversions.fromInt(y)[0];
		out[9] = ByteConversions.fromInt(y)[1];
		out[10] = ByteConversions.fromInt(y)[2];
		out[11] = ByteConversions.fromInt(y)[3];
		out[12] = (byte)fontsize;
		
		for (int i = 0; i < byteText.length; i++) {
			out[i + 13] = byteText[i];
			out[i + 1 + 13] = byteText[i];
		}
		
		sendToServer(out);
	}

	public void colorPixel(int x, int y, int r, int g, int b) throws IOException {
		byte[] out = new byte[14];
		out[0] = CMD_DRAW_PIXEL;
		out[1] = (byte) r;
		out[2] = (byte) g;
		out[3] = (byte) b;
		out[4] = ByteConversions.fromInt(x)[0];
		out[5] = ByteConversions.fromInt(x)[1];
		out[6] = ByteConversions.fromInt(x)[2];
		out[7] = ByteConversions.fromInt(x)[3];
		out[8] = ByteConversions.fromInt(y)[0];
		out[9] = ByteConversions.fromInt(y)[1];
		out[10] = ByteConversions.fromInt(y)[2];
		out[11] = ByteConversions.fromInt(y)[3];
		sendToServer(out);
	}
	
	public void drawRectangle(int x, int y, int r, int g, int b) throws IOException {
		byte[] out = new byte[14];
		out[0] = CMD_DRAW_PIXEL;
		out[1] = (byte) r;
		out[2] = (byte) g;
		out[3] = (byte) b;
		out[4] = ByteConversions.fromInt(x)[0];
		out[5] = ByteConversions.fromInt(x)[1];
		out[6] = ByteConversions.fromInt(x)[2];
		out[7] = ByteConversions.fromInt(x)[3];
		out[8] = ByteConversions.fromInt(y)[0];
		out[9] = ByteConversions.fromInt(y)[1];
		out[10] = ByteConversions.fromInt(y)[2];
		out[11] = ByteConversions.fromInt(y)[3];
		sendToServer(out);
	}
	
	private void sendToServer(byte[] bytes) throws IOException {
		Socket connection = new Socket(ip, 8088);
		connection.getOutputStream().write(bytes);
		if(connection.getInputStream().read() != 0xAA) {
			connection.close();
			throw new IOException();
		}
		connection.close();
	}

	public static final byte CMD_DRAW_PIXEL = 0x01;
	public static final byte CMD_DRAW_RECTANGLE = 0x02;
	public static final byte CMD_DRAW_LINE = 0x03;
	public static final byte CMD_DRAW_CIRCLE = 0x04;
	public static final byte CMD_DRAW_TEXT = 0x05;
}
