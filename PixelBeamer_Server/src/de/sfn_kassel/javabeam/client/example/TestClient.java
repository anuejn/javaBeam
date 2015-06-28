package de.sfn_kassel.javabeam.client.example;

import java.awt.Color;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import de.sfn_kassel.javabeam.client.DrawErrorException;
import de.sfn_kassel.javabeam.client.JavabeamClient;
import de.sfn_kassel.javabeam.util.sprites.Rectangle;
import de.sfn_kassel.javabeam.util.sprites.Text;

public class TestClient {
	
	public static void main(String[] args) throws DrawErrorException, UnknownHostException {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		int offsetX = new Random().nextInt(600);
		int offsetY = new Random().nextInt(600);
		beamer.sendToServer(new Rectangle(10 + offsetX, 10 + offsetY, 100, 50, Color.CYAN));
		beamer.sendToServer(new Text(15 + offsetX, 45 + offsetY, InetAddress.getLocalHost().getHostName(), 20));
	}
	
}
