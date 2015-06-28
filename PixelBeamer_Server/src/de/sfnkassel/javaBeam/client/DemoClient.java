package de.sfnkassel.javaBeam.client;

import java.awt.Color;
import java.io.IOException;

public class DemoClient {
	
	public static void main(String[] args) {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		try {
			beamer.drawText(100, 300, new Color(0, 127, 127), 30, "Hello, World");
			beamer.drawPixel(5, 100, new Color(0, 0, 0));
			beamer.drawCircle(300, 300, 50, new Color(0, 0, 0));
			beamer.drawLine(0, 0, 500, 500, 10, new Color(255, 0, 0));
			beamer.drawRectangle(400, 10, 20, 20, new Color(0, 0, 255));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
