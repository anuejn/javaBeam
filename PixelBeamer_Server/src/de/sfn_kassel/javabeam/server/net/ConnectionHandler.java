package de.sfn_kassel.javabeam.server.net;

import static de.sfn_kassel.javabeam.server.Main.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import de.sfn_kassel.javabeam.server.draw.Drawer;
import de.sfn_kassel.javabeam.util.Drawable;

public class ConnectionHandler extends Thread {

	private ServerSocket socket;
	private boolean shouldRun = false;
	public List<Drawable> commands = new ArrayList<>();
	
	public ConnectionHandler(Drawer drawer) throws IOException{
		socket = new ServerSocket(8088);
		shouldRun = true;
	}

	@Override
	public void run() {
		while(shouldRun){
			try {
				new RequestProcessor(socket.accept(), this).start();
			} catch(IOException e){
				if (socket.isClosed()) info("Socket Closed during Bind-Phase");
				else fatal(e);
			}
		}
	}
	
	public void proposeStop(){
		shouldRun = false;
		try {
			socket.close();
		} catch (IOException e) {
			fatal(e);
		}
	}
	
}
