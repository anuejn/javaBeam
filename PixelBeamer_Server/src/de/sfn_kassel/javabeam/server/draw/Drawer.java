package de.sfn_kassel.javabeam.server.draw;

import de.sfn_kassel.javabeam.util.Drawable;
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
	    blur.setWidth(1.55);
	    blur.setHeight(1.55);
	    blur.setIterations(20);
	    graphics.setEffect(blur);
	}
	
	public void drawCommand(Drawable command) {
		command.draw(graphics);
	}

//	public void drawCommand(Byte[] command) throws UnsupportedOperationException{
//		switch(command[0]){
//			case CMD_DRAW_PIXEL:
//				graphics.setFill(createColor(command));
//				graphics.fillRect(intFromByteArray(ArrayUtil.getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 8, 4)), 1, 1);
//				break;
//			case CMD_DRAW_RECTANGLE:
//				graphics.setFill(createColor(command));
//				graphics.fillRect(intFromByteArray(ArrayUtil.getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 8, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 12, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 16, 4)));
//				break;
//			case CMD_DRAW_LINE:
//				graphics.setStroke(createColor(command));
//				graphics.setLineWidth(command[20]);
//				graphics.strokeLine(intFromByteArray(ArrayUtil.getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 8, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 12, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 16, 4)));
//				break;
//			case CMD_DRAW_CIRCLE:
//				graphics.setFill(createColor(command));
//				graphics.fillOval(intFromByteArray(ArrayUtil.getSubarray(command, 4, 4)) - intFromByteArray(ArrayUtil.getSubarray(command, 12, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 8, 4)) - intFromByteArray(ArrayUtil.getSubarray(command, 12, 4)), 2 * intFromByteArray(ArrayUtil.getSubarray(command, 12, 4)), 2 * intFromByteArray(ArrayUtil.getSubarray(command, 12, 4)));
//				break;
//			case CMD_DRAW_TEXT:
//				graphics.setFill(createColor(command));
//				graphics.setFont(Font.font ("Calibri", command[12]));
//				String str = "";
//				for(int i = 13; i < command.length; i += 2){
//					str += charFromByteArray(ArrayUtil.getSubarray(command, i, 2));
//				}
//				graphics.fillText(str, intFromByteArray(ArrayUtil.getSubarray(command, 4, 4)), intFromByteArray(ArrayUtil.getSubarray(command, 8, 4)));
//				break;
//			case INVALID:
//				info("Draw command recieved that was marked Invalid.");
//				break;
//			default:
//				throw new UnsupportedOperationException("Unknown draw command recieved.");
//		}
//	}
}
