package de.sfn_kassel.javabeam.client.example;

import java.awt.Color;

import de.sfn_kassel.javabeam.client.DrawErrorException;
import de.sfn_kassel.javabeam.client.JavabeamClient;
import de.sfn_kassel.javabeam.util.sprites.Circle;
import de.sfn_kassel.javabeam.util.sprites.Rectangle;


public class Smily {
	public static void main(String[] args) throws DrawErrorException {
		// Erstellt einen "JavabeamClient", der in der Variable "beamer" gespeichert wird.
		// Der Parameter gibt dabei die IP des Computers an, auf dem das Bild gemalt wird
		JavabeamClient beamer = new JavabeamClient("localhost");
		
		// malt ein weißes Rechteck, um alles "alte" zu übermalen
		beamer.sendToServer(new Rectangle(0, 0, 2000, 1000, Color.GREEN));
		
		// gelberKkreis
		beamer.sendToServer(new Circle(500, 300, 200, Color.YELLOW));
		
		// augen
		beamer.sendToServer(new Circle(500 + 70, 250, 30, Color.BLACK));
		beamer.sendToServer(new Circle(500 - 70, 250, 30, Color.BLACK));
		
		// Diese Schleife läuft 21 mal durch, wobei die Variable x am amfang -100 ist und bei jedem Schleifendurchlauf
		// um 10 erhöht wird.
		// Sobald die Bedingung "x <= 100" nicht mehr erfüllt ist, wird die schleife abgebrochen.
		for (int x = -100; x <= 100; x += 10) {
			// malt (bei jedem Schleifendurchlauf) einen Kreis
			beamer.sendToServer(new Circle(500 + x, (int) (420 - 0.005 * x * x), 10+15*Math.cos(x/100.), Color.BLACK));
		}
	}
}
