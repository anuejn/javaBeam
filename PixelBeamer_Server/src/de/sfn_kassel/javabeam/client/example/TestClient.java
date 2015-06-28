package de.sfn_kassel.javabeam.client.example;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

import de.sfn_kassel.javabeam.client.JavabeamClient;

public class TestClient {
	
	public static void main(String[] args) {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		try {
			int offsetX = new Random().nextInt(600);
			int offsetY = new Random().nextInt(600);
			beamer.drawRectangle(10 + offsetX, 10 + offsetY, 100, 50, Color.CYAN);
			beamer.drawText(15 + offsetX, 45 + offsetY, Color.BLACK, 20, InetAddress.getLocalHost().getHostName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
