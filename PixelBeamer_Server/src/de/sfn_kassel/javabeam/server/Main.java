package de.sfn_kassel.javabeam.server;

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

import de.sfn_kassel.javabeam.server.draw.Drawer;
import de.sfn_kassel.javabeam.server.net.ConnectionHandler;
import de.sfn_kassel.javabeam.util.Drawable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
		try {
			this.handler = new ConnectionHandler(drawer);
		} catch (IOException e) {
			System.err.println("Server konnte nicht erstellt werden! Läuft schon eine Instanz?");
			fatal(e);
			System.exit(1);
		}
		handler.start();
		
		drawSchedule.schedule(new TimerTask(){
			@Override
			public void run() {
				synchronized (handler.commands) {
					for(Drawable cmd : handler.commands){
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
		final String finalIp = internalIp.substring(1);
		
		Drawable out = new Drawable() {
			private static final long serialVersionUID = 2465458178731739235L;

			@Override
			public void draw(GraphicsContext graphics) {
				graphics.fillText(finalIp, 100, 100);
			}
		};
		
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
