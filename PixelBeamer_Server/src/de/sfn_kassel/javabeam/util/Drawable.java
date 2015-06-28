package de.sfn_kassel.javabeam.util;

import java.awt.Color;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable extends Serializable {
	public void draw(GraphicsContext graphics);
	default void setGraphicsFill(GraphicsContext graphics, Color c) {
		graphics.setFill(new javafx.scene.paint.Color(c.getRed()/255.0, c.getGreen()/255.0, c.getBlue()/255.0, 1));
	}
}
