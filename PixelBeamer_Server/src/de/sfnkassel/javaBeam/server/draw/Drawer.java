package de.sfnkassel.pixelbeamer.server.draw;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static de.sfnkassel.pixelbeamer.server.Main.*;

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
	
	/*
	 * Byte 1: Cmd -> 0x01 = Draw a Pixel
	 * 				  0x02 = Draw a Rectangle
	 * 				  0x03 = Draw a Line
	 * 				  0x04 = Draw a Circle
	 * 				  0x05 = Draw a Text
	 * 
	 * Byte 2 - 4: Color -> r, g, b
	 * 
	 * Byte 5 - 8: X-Pos. 1 | Int
	 * 
	 * Byte 9 - 12: Y-Pos. 1 | Int
	 * 
	 * Byte 13 - 20 (bei Rec, Line): X 2, Y 2 | Int
	 * 
	 * Byte 13 - 16 (bei Circle): Radius | Int
	 * 
	 * Byte 13f (je 2, bei Text): Chars | Char
	 */
}
