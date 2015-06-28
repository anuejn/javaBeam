package de.sfnkassel.javaBeam.client.example;

import java.awt.Color;
import java.io.IOException;

import de.sfnkassel.javaBeam.client.JavabeamClient;


public class Smily {
	public static void main(String[] args) throws IOException {
		// Erstellt einen "JavabeamClient", der in der Variable "beamer" gespeichert wird.
		// Der Parameter gibt dabei die IP des Computers an, auf dem das Bild gemalt wird
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		// malt ein wei�es Rechteck, um alles "alte" zu �bermalen
		beamer.drawRectangle(0, 0, 1000, 1000, Color.WHITE);
		
		// gelber krreis
		beamer.drawCircle(500, 300, 200, Color.YELLOW);
		
		// augen
		beamer.drawCircle(500 + 70, 250, 30, Color.BLACK);
		beamer.drawCircle(500 - 70, 250, 30, Color.BLACK);
		
		// Diese Schleife l�uft 21 mal durch, wobei die Variable x am amfang -100 ist und bei jedem Schleifendurchlauf
		// um 10 erh�ht wird.
		// Sobald die Bedingung "x <= 100" nicht mehr erf�llt ist, wird die schleife abgebrochen.
		for (int x = -100; x <= 100; x += 10) {
			// malt (bei jedem Schleifendurchlauf) einen Kreis
			beamer.drawCircle(500 + x, (int) (420 - 0.005 * x * x), 20, Color.BLACK);
		}
	}
}
