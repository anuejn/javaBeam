package de.sfn_kassel.javabeam.util.sprites;

import java.awt.Color;
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import de.sfn_kassel.javabeam.util.Drawable;

public class Text implements Drawable {

	private static final long serialVersionUID = 3260284587622243613L;
	private Point pos;
	private String text;
	private int size;
	private Color color;
	
	public Text(double x, double y, String text) {
		this(x, y, text, 16);
	}
	
	public Text(double x, double y, String text, int size) {
		this(x, y, text, size, Color.BLACK);
	}
	
	public Text(double x, double y, String text, int size, Color color) {
		super();
		this.pos = new Point((int) x, (int) y);
		this.text = text;
		this.size = size;
		this.color = color;
	}

	@Override
	public void draw(GraphicsContext graphics) {
		setGraphicsFill(graphics, color);
		graphics.setFont(Font.font ("Calibri", size));
		graphics.fillText(text, pos.getX(), pos.getY());
	}
	
}
