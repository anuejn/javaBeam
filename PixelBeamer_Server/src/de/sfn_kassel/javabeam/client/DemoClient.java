package de.sfn_kassel.javabeam.client;

import java.awt.Color;
import java.io.IOException;

public class DemoClient {
	
	public static void main(String[] args) {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		try {
			beamer.drawRectangle(0, 0, 10000, 10000, Color.WHITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
