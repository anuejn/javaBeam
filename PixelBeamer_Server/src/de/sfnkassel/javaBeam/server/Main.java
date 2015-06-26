package de.sfnkassel.javaBeam.server;

import java.util.ArrayList;
import java.util.List;

import de.sfnkassel.javaBeam.server.draw.Drawer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	private StackPane mainPane = new StackPane();
	private Canvas drawCanvas;
	private Drawer drawer;
	
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
		this.drawer = new Drawer(drawCanvas);
	}
	
	public void recievedDrawCall(Byte[] command){
		drawer.drawCommand(command);
	}
	
	public static void fatal(Exception e){
		e.printStackTrace(System.err);
		exceptions.add(e);
	}
	
	public static void info(String msg){
		System.out.println(msg);
	}
}
