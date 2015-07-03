package de.sfn_kassel.javabeam.client;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import de.sfn_kassel.javabeam.util.ByteConversions;
import de.sfn_kassel.javabeam.util.SpriteType;

public class JavaBeamClient {
	private String ip;

	/**
	 * 
	 * @param ip Die IP-Adresse des Zielcomputers
	 */
	public JavaBeamClient(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @param x Die X-Koordinate des zu malenden Pixels
	 * @param y Die Y-Koordinate des zu malenden Pixels
	 * @param color Die Farbe des zu malenden Pixels
	 * @throws IOException Falls keine Verbindung aufgebaut werden kann
	 */
	public void drawPixel(int x, int y, Color color) throws IOException, InterruptedException {
		byte[] out = new byte[12];
		out[0] = SpriteType.CMD_DRAW_PIXEL;
		out[1] = (byte) color.getRed();
		out[2] = (byte) color.getGreen();
		out[3] = (byte) color.getBlue();
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
	
	/**
	 * 
	 * @param x Die X-Koordinate der oberen Linken Ecke des Rechtecks
	 * @param y Die Y-Koordinate der oberen Linken Ecke des Rechtecks
	 * @param width Die Breite des Rechtecks
	 * @param height Die Höhe des Rechtecks
	 * @param color Die Farbe des Rechtecks
	 * @throws IOException Falls keine Verbindung aufgebaut werden kann
	 */
	public void drawRectangle(int x, int y, int width, int height, Color color) throws IOException, InterruptedException {
		byte[] out = new byte[20];
		out[0] = SpriteType.CMD_DRAW_RECTANGLE;
		out[1] = (byte) color.getRed();
		out[2] = (byte) color.getGreen();
		out[3] = (byte) color.getBlue();
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
	
	/**
	 * 
	 * @param x1 Die X-Koordinate des Startpunktes der Linie
	 * @param y1 Die Y-Koordinate des Startpunktes der Linie
	 * @param x2 Die X-Koordinate des Endpunktes der Linie
	 * @param y2 Die Y-Koordinate des Endpunktes der Linie
	 * @param thickness Die Dicke der Linie
	 * @param color Die Farbe der Linie
	 * @throws IOException Falls keine Verbindung aufgebaut werden kann
	 */
	public void drawLine(int x1, int y1, int x2, int y2, int thickness, Color color) throws IOException, InterruptedException {
		byte[] out = new byte[21];
		out[0] = SpriteType.CMD_DRAW_LINE;
		out[1] = (byte) color.getRed();
		out[2] = (byte) color.getGreen();
		out[3] = (byte) color.getBlue();
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
		out[20] = (byte) thickness;
		sendToServer(out);
	}
	
	/**
	 * 
	 * @param x Die X-Koordinate der Kreismitte
	 * @param y Die Y-Koordinate der Kreismitte
	 * @param rad Der Radius des Kreises
	 * @param color Die Farbe des Kreises
	 * @throws IOException Falls keine Verbindung aufgebaut werden kann
	 */
	public void drawCircle(int x, int y, int rad, Color color) throws IOException, InterruptedException {
		byte[] out = new byte[16];
		out[0] = SpriteType.CMD_DRAW_CIRCLE;
		out[1] = (byte) color.getRed();
		out[2] = (byte) color.getGreen();
		out[3] = (byte) color.getBlue();
		out[4] = ByteConversions.fromInt(x)[0];
		out[5] = ByteConversions.fromInt(x)[1];
		out[6] = ByteConversions.fromInt(x)[2];
		out[7] = ByteConversions.fromInt(x)[3];
		out[8] = ByteConversions.fromInt(y)[0];
		out[9] = ByteConversions.fromInt(y)[1];
		out[10] = ByteConversions.fromInt(y)[2];
		out[11] = ByteConversions.fromInt(y)[3];
		out[12] = ByteConversions.fromInt(rad)[0];
		out[13] = ByteConversions.fromInt(rad)[1];
		out[14] = ByteConversions.fromInt(rad)[2];
		out[15] = ByteConversions.fromInt(rad)[3];
		sendToServer(out);
	}
	
	/**
	 * 
	 * @param x Die X-Koordinate der unteren Linken Ecke des Textes
	 * @param y Die Y-Koordinate der unteren Linken Ecke des Textes
	 * @param color Die Farbe des Textes
	 * @param fontsize Die Schriftgröße des Textes
	 * @param text Der Text
	 * @throws IOException Falls keine Verbindung aufgebaut werden kann
	 */
	public void drawText(int x, int y, Color color, int fontsize, String text) throws IOException, InterruptedException {
		byte[] byteText = ByteConversions.stringToByteArray(text);
		byte[] out = new byte[13 + byteText.length];
		out[0] = SpriteType.CMD_DRAW_TEXT;
		out[1] = (byte) color.getRed();
		out[2] = (byte) color.getGreen();
		out[3] = (byte) color.getBlue();
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
	
	private void sendToServer(byte[] bytes) throws IOException, InterruptedException {
		Socket connection = new Socket(ip, 8088);
		connection.getOutputStream().write(bytes);
		connection.close();
		Thread.sleep(10);
	}
}
