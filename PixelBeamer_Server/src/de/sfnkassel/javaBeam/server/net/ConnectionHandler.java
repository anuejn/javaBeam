package de.sfnkassel.javaBeam.server.net;

import static de.sfnkassel.javaBeam.server.Main.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import de.sfnkassel.javaBeam.server.draw.Drawer;

public class ConnectionHandler extends Thread {

	private ServerSocket socket;
	private boolean shouldRun = false;
	public List<Byte[]> commands = new ArrayList<Byte[]>();
	
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
