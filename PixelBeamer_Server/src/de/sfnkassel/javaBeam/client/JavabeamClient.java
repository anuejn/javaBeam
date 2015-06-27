package de.sfnkassel.javaBeam.client;

import java.io.IOException;
import java.net.Socket;

import de.sfnkassel.javaBeam.server.util.ByteConversions;
import de.sfnkassel.javaBeam.server.util.SpriteType;

public class JavabeamClient {
	private String ip;

	public JavabeamClient(String ip) {
		this.ip = ip;
	}
	
	public static void main(String[] args) throws IOException {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		beamer.drawText(100, 300, 0, 127, 127, 30, "Hello, World");
	}

	public void colorPixel(int x, int y, int r, int g, int b) throws IOException {
		byte[] out = new byte[14];
		out[0] = SpriteType.CMD_DRAW_PIXEL;
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
	
	public void drawRectangle(int x, int y, int width, int height, int r, int g, int b) throws IOException {
		byte[] out = new byte[14];
		out[0] = SpriteType.CMD_DRAW_RECTANGLE;
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
		out[12] = ByteConversions.fromInt(width)[0];
		out[13] = ByteConversions.fromInt(width)[1];
		out[14] = ByteConversions.fromInt(width)[2];
		out[15] = ByteConversions.fromInt(width)[3];
		out[16] = ByteConversions.fromInt(height)[0];
		out[17] = ByteConversions.fromInt(height)[1];
		out[18] = ByteConversions.fromInt(height)[2];
		out[19] = ByteConversions.fromInt(height)[3];
		sendToServer(out);
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, int r, int g, int b) throws IOException {
		byte[] out = new byte[14];
		out[0] = SpriteType.CMD_DRAW_LINE;
		out[1] = (byte) r;
		out[2] = (byte) g;
		out[3] = (byte) b;
		out[4] = ByteConversions.fromInt(x1)[0];
		out[5] = ByteConversions.fromInt(x1)[1];
		out[6] = ByteConversions.fromInt(x1)[2];
		out[7] = ByteConversions.fromInt(x1)[3];
		out[8] = ByteConversions.fromInt(y1)[0];
		out[9] = ByteConversions.fromInt(y1)[1];
		out[10] = ByteConversions.fromInt(y1)[2];
		out[11] = ByteConversions.fromInt(y1)[3];
		out[12] = ByteConversions.fromInt(x2)[0];
		out[13] = ByteConversions.fromInt(x2)[1];
		out[14] = ByteConversions.fromInt(x2)[2];
		out[15] = ByteConversions.fromInt(x2)[3];
		out[16] = ByteConversions.fromInt(y2)[0];
		out[17] = ByteConversions.fromInt(y2)[1];
		out[18] = ByteConversions.fromInt(y2)[2];
		out[19] = ByteConversions.fromInt(y2)[3];
		sendToServer(out);
	}
	
	public void drawCircle(int x1, int y1, int rad, int r, int g, int b) throws IOException {
		byte[] out = new byte[14];
		out[0] = SpriteType.CMD_DRAW_CIRCLE;
		out[1] = (byte) r;
		out[2] = (byte) g;
		out[3] = (byte) b;
		out[4] = ByteConversions.fromInt(x1)[0];
		out[5] = ByteConversions.fromInt(x1)[1];
		out[6] = ByteConversions.fromInt(x1)[2];
		out[7] = ByteConversions.fromInt(x1)[3];
		out[8] = ByteConversions.fromInt(y1)[0];
		out[9] = ByteConversions.fromInt(y1)[1];
		out[10] = ByteConversions.fromInt(y1)[2];
		out[11] = ByteConversions.fromInt(y1)[3];
		out[12] = ByteConversions.fromInt(r)[0];
		out[13] = ByteConversions.fromInt(r)[1];
		out[14] = ByteConversions.fromInt(r)[2];
		out[15] = ByteConversions.fromInt(r)[3];
		sendToServer(out);
	}
	
	public void drawText(int x, int y, int r, int g, int b, int fontsize, String text) throws IOException {
		byte[] byteText = ByteConversions.stringToByteArray(text);
		byte[] out = new byte[13 + byteText.length];
		out[0] = SpriteType.CMD_DRAW_TEXT;
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
		}
		
		sendToServer(out);
	}
	
	private void sendToServer(byte[] bytes) throws IOException {
		Socket connection = new Socket(ip, 8088);
		connection.getOutputStream().write(bytes);
		if(connection.getInputStream().read() != 0xAA) {
			connection.close();
			throw new IOException("cmd not sucess");
		}
		connection.close();
	}
}
