package de.sfnkassel.javaBeam.client.example;

import java.awt.Color;
import java.io.IOException;

import de.sfnkassel.javaBeam.client.JavabeamClient;

public class Smily {
	public static void main(String[] args) throws IOException {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		beamer.drawRectangle(0, 0, 1000, 1000, Color.WHITE);
		beamer.drawCircle(500, 300, 200, Color.YELLOW);
		beamer.drawCircle(500+70, 250, 30, Color.BLACK);
		beamer.drawCircle(500-70, 250, 30, Color.BLACK);
		
		for (int x = -100; x < 100; x += 10) {
			beamer.drawCircle(500+x, (int) (420-0.005*x*x), 20, Color.BLACK);
		}
	}
}
