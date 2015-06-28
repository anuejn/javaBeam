package de.sfn_kassel.javabeam.server.net;

import static de.sfn_kassel.javabeam.server.Main.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import de.sfn_kassel.javabeam.util.Drawable;


public class RequestProcessor extends Thread {
	
	private Socket socket;
	
	private ConnectionHandler handler;
	
	public RequestProcessor(Socket socket, ConnectionHandler handler) {
		this.handler = handler;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
				try {
					Drawable d = (Drawable) ois.readObject();
					info("Recieved Draw Call: " + d);
					synchronized (handler.commands) {
						handler.commands.add(d);
					}
					oos.writeObject("done");
				} catch (Exception e) {
					e.printStackTrace();
					oos.writeObject(e);
				}
			oos.flush();
			oos.close();
		} catch (IOException e) {
			fatal(e);
		}
		finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				fatal(e);
			}
		}
	}
}
