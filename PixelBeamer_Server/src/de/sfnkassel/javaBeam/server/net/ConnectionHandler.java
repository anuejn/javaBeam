package de.sfnkassel.javaBeam.server.net;

import static de.sfnkassel.javaBeam.server.Main.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import de.sfnkassel.javaBeam.server.draw.Drawer;
import de.sfnkassel.javaBeam.server.util.ArrayUtil;
import javafx.application.Platform;

public class ConnectionHandler extends Thread {

	private ServerSocket socket;
	private Socket tmpSocket;
	private OutputStream out;
	private InputStream in;
	private Byte[] bytes;
	private boolean shouldRun = false;
	private Drawer drawer;
	
	public ConnectionHandler(Drawer drawer) throws IOException{
		socket = new ServerSocket(8088);
		shouldRun = true;
		this.drawer = drawer;
	}

	@Override
	public void run() {
		while(shouldRun){
			try {
				tmpSocket = socket.accept();
				in = tmpSocket.getInputStream();
				out = tmpSocket.getOutputStream();
				bytes = new Byte[in.available()];
				for(int i = 0; i < in.available(); i++){
					bytes[i] = (byte) in.read();
				}
				out.write(new byte[]{(byte) 0xAA}, 0, 1);
				out.flush();
				
				drawCall(ArrayUtil.deepCopyArray(bytes));
				
				String cmd = "";
				for(byte b : bytes){
					cmd += b + "; ";
				}
				info("Recieved Draw Call: " + cmd.substring(0, cmd.length() - 3));
			} catch (IOException e) {
				if(socket.isClosed())
					info("Socket Closed during Bind-Phase");
				else
					fatal(e);
			} finally {
				try {
					if (tmpSocket != null)
						tmpSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					fatal(e);
				}
			}
		}
	}
	
	private void drawCall(Byte[] command){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				drawer.drawCommand(bytes);
			}
		});
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
