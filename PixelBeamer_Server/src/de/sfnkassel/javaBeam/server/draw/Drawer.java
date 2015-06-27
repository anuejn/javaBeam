package de.sfnkassel.javaBeam.server.draw;

import static de.sfnkassel.javaBeam.server.Main.*;
import static de.sfnkassel.javaBeam.server.util.ByteConversions.*;

import de.sfnkassel.javaBeam.server.util.ArrayUtil;
import de.sfnkassel.javaBeam.server.util.ByteConversions;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.text.Font;

public class Drawer {
	
	GraphicsContext graphics;
	
	public Drawer(Canvas canvas){
		graphics = canvas.getGraphicsContext2D();
		graphics.setFont(Font.font ("Calibri", 20));
		BoxBlur blur = new BoxBlur();
	    blur.setWidth(1);
	    blur.setHeight(1);
	    blur.setIterations(1);
	    graphics.setEffect(blur);
	}
	
	public void drawCommand(Byte[] command) throws UnsupportedOperationException{
		switch(command[0]){
			case CMD_DRAW_PIXEL:
				graphics.setFill(ByteConversions.colorFromByteArray(command, 1));
				graphics.fillRect(intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 8, 4)), 1, 1);
				break;
			case CMD_DRAW_RECTANGLE:
				graphics.setFill(ByteConversions.colorFromByteArray(command, 1));
				graphics.fillRect(intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 8, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 12, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 16, 4)));
				break;
			case CMD_DRAW_LINE:
				graphics.setStroke(ByteConversions.colorFromByteArray(command, 1));
				graphics.setLineWidth(ByteConversions.colorPartFromByte(command[20]));
				graphics.strokeLine(intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 8, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 12, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 16, 4)));
				break;
			case CMD_DRAW_CIRCLE:
				graphics.setFill(ByteConversions.colorFromByteArray(command, 1));
				graphics.fillOval(intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 4, 4)) - intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 12, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 8, 4)) - intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 12, 4)), 2 * intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 12, 4)), 2 * intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 12, 4)));
				break;
			case CMD_DRAW_TEXT:
				graphics.setFill(ByteConversions.colorFromByteArray(command, 1));
				graphics.setFont(Font.font ("Calibri", command[12]));
				String str = "";
				for(int i = 13; i < command.length; i += 2){
					str += charFromByteArray(ArrayUtil.<Byte>getSubarray(command, i, 2));
				}
				graphics.fillText(str, intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.<Byte>getSubarray(command, 8, 4)));
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
