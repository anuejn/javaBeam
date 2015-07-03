package de.sfn_kassel.javabeam.server;

import java.awt.Color;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.sfn_kassel.javabeam.client.JavabeamClient;
import de.sfn_kassel.javabeam.server.draw.Drawer;
import de.sfn_kassel.javabeam.server.net.ConnectionHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
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
		drawCanvas = new Canvas(primaryStage.getWidth(),
				primaryStage.getHeight());
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
		try {
			this.handler = new ConnectionHandler(drawer);
			handler.start();
		} catch (IOException e) {
			fatal(new IOException("Couldn't start Server. Already running?", e));
			System.exit(1);
		}
		
		drawSchedule.schedule(new TimerTask() {
			
			@Override
			public void run() {
				synchronized (handler.commands) {
					for(Byte[] cmd : handler.commands){
						Platform.runLater(() -> {
							drawer.drawCommand(cmd);
						});
					}
					handler.commands.clear();
				}
			}
		}, 100, 100);
		
		initDrawing();
	}
	
	private void initDrawing() {
		String internalIp = "";
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = netInterfaces.nextElement();
				if (!netInterface.isUp() || netInterface.isLoopback()
						|| netInterface.isVirtual())
					continue;
				Enumeration<InetAddress> addrs = netInterface
						.getInetAddresses();
				while (addrs.hasMoreElements()) {
					InetAddress addr = addrs.nextElement();
					if (!addr.isLoopbackAddress()
							&& addr instanceof Inet4Address) {
						internalIp = addr.toString();
						break;
					}
				}
				if (!internalIp.equals("")) break;
			}
		} catch (SocketException e) {
			fatal(e);
		}
		internalIp = internalIp.substring(1);
		JavabeamClient me = new JavabeamClient(internalIp);
		int fontSize = 42;
		try {
			me.drawText((int)drawCanvas.getWidth() / 2 - ((internalIp.length() * fontSize) / 4), (int)drawCanvas.getHeight() - 1, Color.BLACK, fontSize, internalIp);
			drawHint(0, 0, me);
			for(int i = 0; i < 10; i ++) {
				drawHint((int)(Math.random() * 1000), (int)(Math.random() * 700), me);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fatal(Exception e) {
		System.err.println("[SEVERE]");
		e.printStackTrace(System.err);
		exceptions.add(e);
	}
	
	public static void info(String msg) {
		System.out.println("[INFO]");
		System.out.println(msg);
	}
	
	public static void drawHint(int x, int y, JavabeamClient me) throws IOException {
		me.drawCircle(x, y, 3, Color.DARK_GRAY);
		me.drawLine(x + 10, y + 10, x + 20, y+20, 2, Color.DARK_GRAY);
		me.drawLine(x+ 10, y+10, x+10, y+20, 2, Color.DARK_GRAY);
		me.drawLine(x+10, y+10, x+20, y+10, 2, Color.DARK_GRAY);
		me.drawText(x+30, y+30, Color.DARK_GRAY, 10, "(" + x + "|"+y+")");
	}
}
