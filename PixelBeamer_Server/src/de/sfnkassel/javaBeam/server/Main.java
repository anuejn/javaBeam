package de.sfnkassel.javaBeam.server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.sfnkassel.javaBeam.server.draw.Drawer;
import de.sfnkassel.javaBeam.server.net.ConnectionHandler;
import de.sfnkassel.javaBeam.util.ByteConversions;
import de.sfnkassel.javaBeam.util.SpriteType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	private StackPane mainPane = new StackPane();
	private Canvas drawCanvas;
	private Drawer drawer;
	private ConnectionHandler handler;
	
	private static List<Exception> exceptions = new ArrayList<Exception>();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("PixelBeamer");
		drawCanvas = new Canvas(primaryStage.getWidth(), primaryStage.getHeight());
		drawCanvas.widthProperty().bind(mainPane.widthProperty());
		drawCanvas.heightProperty().bind(mainPane.heightProperty());
		mainPane.getChildren().add(drawCanvas);
		Timer drawSchedule = new Timer();
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
		    info("Closing Application.");
		    handler.proposeStop();
		    drawSchedule.cancel();
		});
		
		this.drawer = new Drawer(drawCanvas);
		this.handler = new ConnectionHandler(drawer);
		handler.start();
		
		drawSchedule.schedule(new TimerTask(){
			@Override
			public void run() {
				for(Byte[] cmd : handler.commands){
					Platform.runLater(() -> {
						drawer.drawCommand(cmd);
					});
				}
				handler.commands.clear();
			}
		}, 100, 100);
		
		initDrawing();
	}
	
	private void initDrawing(){
		String internalIp = "";
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while(netInterfaces.hasMoreElements()){
				NetworkInterface netInterface = netInterfaces.nextElement();
				if(!netInterface.isUp() || netInterface.isLoopback() || netInterface.isVirtual()) continue;
				Enumeration<InetAddress> addrs = netInterface.getInetAddresses();
				while(addrs.hasMoreElements()){
					InetAddress addr = addrs.nextElement();
					if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
						internalIp = addr.toString();
						break;
					}
				}
				if(!internalIp.equals(""))
					break;
			}
		} catch (SocketException e) {
			fatal(e);
		}
		
		byte[] byteText = ByteConversions.stringToByteArray(internalIp.substring(1));
		Byte[] out = new Byte[13 + byteText.length];
		out[0] = SpriteType.CMD_DRAW_TEXT;
		out[1] = 0;
		out[2] = 0;
		out[3] = 0;
		out[4] = ByteConversions.fromInt(300)[0];
		out[5] = ByteConversions.fromInt(300)[1];
		out[6] = ByteConversions.fromInt(300)[2];
		out[7] = ByteConversions.fromInt(300)[3];
		out[8] = ByteConversions.fromInt(400)[0];
		out[9] = ByteConversions.fromInt(400)[1];
		out[10] = ByteConversions.fromInt(400)[2];
		out[11] = ByteConversions.fromInt(400)[3];
		out[12] = 88;
		
		for (int i = 0; i < byteText.length; i++) {
			out[i + 13] = byteText[i];
		}
		
		drawer.drawCommand(out);
	}
		
	public static void fatal(Exception e){
		System.err.println("[SEVERE]");
		e.printStackTrace(System.err);
		exceptions.add(e);
	}
	
	public static void info(String msg){
		System.out.println("[INFO]");
		System.out.println(msg);
	}
}
