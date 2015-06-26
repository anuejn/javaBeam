package de.sfnkassel.javaBeam.server;

import java.util.ArrayList;
import java.util.List;

import de.sfnkassel.javaBeam.server.draw.Drawer;
import de.sfnkassel.javaBeam.server.net.ConnectionHandler;
import javafx.application.Application;
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
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
		    info("Closing Application.");
		    handler.proposeStop();
		});
		this.drawer = new Drawer(drawCanvas);
		this.handler = new ConnectionHandler(drawer);
		handler.start();		
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
