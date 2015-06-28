package de.sfn_kassel.javabeam.util.sprites;

import java.awt.Color;
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import de.sfn_kassel.javabeam.util.Drawable;

public class Rectangle implements Drawable {

	private static final long serialVersionUID = 3260284587622243613L;
	private Point pos;
	private double width, height;
	private Color color;
	
	public Rectangle(double x, double y, double width, double height, Color color) {
		super();
		this.pos = new Point((int) x, (int) y);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void draw(GraphicsContext graphics) {
		setGraphicsFill(graphics, color);
		graphics.fillRect(pos.getX(), pos.getY(), width, height);
	}
	
}
