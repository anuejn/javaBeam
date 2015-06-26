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
		
		beamer.drawText(10, 30, 0, 0, 0, "olol");
	}

	public void drawText(int x, int y, int r, int g, int b, String text) throws IOException {
		byte[] byteText = ByteConversions.stringToByteArray(text);
		byte[] out = new byte[13 + text.length() * 2];
		for (int i = 0; i < out.length; i++) {
			if (i == 0) {
				out[i] = 0x05;
			} else if (i == 1) {
				out[i] = (byte) r;
			} else if (i == 2) {
				out[i] = (byte) g;
			} else if (i == 3) {
				out[i] = (byte) b;
			} else if (i == 4 || i == 5) {
				out[i] = x == 4 ? ByteConversions.fromInt(x)[0] : ByteConversions.fromInt(x)[1];
			} else if (i == 6 || i == 7) {
				out[i] = x == 6 ? ByteConversions.fromInt(y)[0] : ByteConversions.fromInt(y)[1];
			} else if (i > 13) {
				out[i] = byteText[i - 13];
			}
		}
		sendToServer(out);
	}

//	public void colorPixel(int x, int y, int r, int g, int b) {
//		byte[] out = new byte[];
//		for (int i = 0; i < out.length; i++) {
//			if (i == 0) {
//				out[i] = 0x05;
//			} else if (i == 1) {
//				out[i] = (byte) r;
//			} else if (i == 2) {
//				out[i] = (byte) g;
//			} else if (i == 3) {
//				out[i] = (byte) b;
//			} else if (i == 4 || i == 5) {
//				out[i] = x == 4 ? ByteConversions.fromInt(x)[0] : ByteConversions.fromInt(x)[1];
//			} else if (i == 6 || i == 7) {
//				out[i] = x == 6 ? ByteConversions.fromInt(y)[0] : ByteConversions.fromInt(y)[1];
//			} else if (i > 13) {
//				out[i] = byteText[i - 13];
//			}
//		}
//		sendToServer(out);
//	}
	
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
