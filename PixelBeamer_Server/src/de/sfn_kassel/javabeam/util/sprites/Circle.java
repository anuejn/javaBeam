package de.sfn_kassel.javabeam.util.sprites;

import java.awt.Color;
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import de.sfn_kassel.javabeam.util.Drawable;

public class Circle implements Drawable {

	private static final long serialVersionUID = 2942099296163809917L;
	private Point pos;
	private double radius;
	private Color color;
	
	public Circle(double x, double y, double radius, Color color) {
		super();
		this.pos = new Point((int) x, (int) y);
		this.radius = radius;
		this.color = color;
	}

	@Override
	public void draw(GraphicsContext graphics) {
		setGraphicsFill(graphics, color);
		graphics.fillOval(pos.getX()-radius, pos.getY()-radius, 2*radius, 2*radius);
	}
	
}
