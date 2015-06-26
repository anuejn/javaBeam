package de.sfnkassel.javaBeam.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JavabeamClient {
	private URL ip;
	
	public JavabeamClient(URL ip) {
		this.ip = ip;
	}
	
	public JavabeamClient(String ip) throws MalformedURLException {
		this.ip = new URL(ip);
	}
	
	public void drawText(String text) {
		
	}
	
	private void sendToServer(byte[] bytes) throws IOException {
		URLConnection connection = ip.openConnection();
		connection.getOutputStream().write(bytes);
		if(connection.getInputStream().read() == 0xAA) {
			
		} else {
			throw new IOException();
		}
	}
	
	
	public static final byte CMD_DRAW_PIXEL = 0x01;
	public static final byte CMD_DRAW_RECTANGLE = 0x02;
	public static final byte CMD_DRAW_LINE = 0x03;
	public static final byte CMD_DRAW_CIRCLE = 0x04;
	public static final byte CMD_DRAW_TEXT = 0x05;
}
