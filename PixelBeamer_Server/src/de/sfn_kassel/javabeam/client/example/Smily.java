package de.sfn_kassel.javabeam.client.example;

import java.awt.Color;
import java.io.IOException;

import de.sfn_kassel.javabeam.client.JavaBeamClient;


public class Smily {
	public static void main(String[] args) throws IOException, InterruptedException {
		// Erstellt einen "JavabeamClient", der in der Variable "beamer" gespeichert wird.
		// Der Parameter gibt dabei die IP des Computers an, auf dem das Bild gemalt wird
		JavaBeamClient beamer = new JavaBeamClient("localhost");
		
		// gelberKkreis
		beamer.drawCircle(500, 300, 200, Color.YELLOW);
		
		// augen
		beamer.drawCircle(500 + 70, 250, 30, Color.BLACK);
		beamer.drawCircle(500 - 70, 250, 30, Color.BLACK);
		
		// Diese Schleife läuft 21 mal durch, wobei die Variable x am amfang -100 ist und bei jedem Schleifendurchlauf
		// um 10 erhöht wird.
		// Sobald die Bedingung "x <= 100" nicht mehr erfüllt ist, wird die schleife abgebrochen.
		for (int x = -100; x <= 100; x += 1) {
			// malt (bei jedem Schleifendurchlauf) einen Kreis
			beamer.drawCircle(500 + x, (int) (420 - 0.005 * x * x), 20, Color.BLACK);
		}
	}
}
