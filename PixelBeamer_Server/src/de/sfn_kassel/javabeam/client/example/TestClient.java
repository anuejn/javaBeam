package de.sfn_kassel.javabeam.client.example;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

import de.sfn_kassel.javabeam.client.JavaBeamClient;

public class TestClient {

	public static void main(String[] args) {
		JavaBeamClient beamer = new JavaBeamClient("localhost");
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				int offsetX = new Random().nextInt(600);
				int offsetY = new Random().nextInt(600);
				beamer.drawRectangle(10 + offsetX, 10 + offsetY, 100, 40, new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
				beamer.drawText(15 + offsetX, offsetY + 30
						, Color.BLACK, 12, InetAddress.getLocalHost().getHostName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
