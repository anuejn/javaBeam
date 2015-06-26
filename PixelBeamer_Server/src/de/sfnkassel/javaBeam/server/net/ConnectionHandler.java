package de.sfnkassel.javaBeam.server.net;

import static de.sfnkassel.javaBeam.server.Main.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import de.sfnkassel.javaBeam.server.draw.Drawer;

public class ConnectionHandler implements Runnable {

	private ServerSocket socket;
	private Socket tmpSocket;
	private ByteArrayOutputStream out;
	private ByteArrayInputStream in;
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
				in = (ByteArrayInputStream) tmpSocket.getInputStream();
				out = (ByteArrayOutputStream) tmpSocket.getOutputStream();
				bytes = new Byte[in.available()];
				for(int i = 0; i < in.available(); i++){
					bytes[i] = (byte) in.read();
				}
				out.write(new byte[]{(byte) 0xAA}, 0, 1);
				out.flush();
				
				drawer.drawCommand(bytes);
				
				String cmd = "";
				for(byte b : bytes){
					cmd += b + "; ";
				}
				info("Recieved Draw Call: " + cmd.substring(0, cmd.length() - 3));
			} catch (IOException e) {
				fatal(e);
			} finally {
				try {
					tmpSocket.close();
				} catch (IOException e) {
					fatal(e);
				}
			}
		}
	}
	
	public void proposeStop(){
		shouldRun = false;
	}
	
}