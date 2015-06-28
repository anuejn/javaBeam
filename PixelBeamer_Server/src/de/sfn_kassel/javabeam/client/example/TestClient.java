package de.sfn_kassel.javabeam.client.example;

import java.awt.Color;
import java.io.IOException;

import de.sfn_kassel.javabeam.client.JavabeamClient;

public class TestClient {
	
	public static void main(String[] args) {
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		try {
			beamer.drawRectangle(10, 10, 10, 10, Color.CYAN);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
