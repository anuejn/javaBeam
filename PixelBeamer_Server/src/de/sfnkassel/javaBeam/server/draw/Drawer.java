package de.sfnkassel.javaBeam.server.draw;

import static de.sfnkassel.javaBeam.server.Main.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Drawer {
	
	GraphicsContext graphics;
	
	public Drawer(Canvas canvas){
		graphics = canvas.getGraphicsContext2D();
	}
	
	public void drawCommand(byte[] command) throws UnsupportedOperationException{
		switch(command[0]){
			case CMD_DRAW_PIXEL:
				break;
			case CMD_DRAW_RECTANGLE:
				break;
			case CMD_DRAW_LINE:
				break;
			case CMD_DRAW_CIRCLE:
				break;
			case CMD_DRAW_TEXT:
				break;
			case INVALID:
				info("Draw command recieved that was marked Invalid.");
				break;
			default:
				throw new UnsupportedOperationException("Unknown draw command recieved.");
		}
	}
	
	public static final byte CMD_DRAW_PIXEL = 0x01;
	public static final byte CMD_DRAW_RECTANGLE = 0x02;
	public static final byte CMD_DRAW_LINE = 0x03;
	public static final byte CMD_DRAW_CIRCLE = 0x04;
	public static final byte CMD_DRAW_TEXT = 0x05;
	
	public static final byte INVALID = 0x00;
}
