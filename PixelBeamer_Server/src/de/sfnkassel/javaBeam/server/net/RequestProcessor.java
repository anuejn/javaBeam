package de.sfnkassel.javaBeam.server.net;

import static de.sfnkassel.javaBeam.server.Main.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import de.sfnkassel.javaBeam.util.ArrayUtil;


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
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			Thread.sleep(100);
			Byte[] bytes = new Byte[in.available()];
			int available = in.available();
			for (int i = 0; i < available; i++ ) {
				bytes[i] = (byte) in.read();
			}
			out.write(new byte[]{(byte) 0xAA}, 0, 1);
			out.flush();
			
			handler.commands.add(ArrayUtil.deepCopyArray(bytes));
			
			String cmd = "";
			for (byte b : bytes) {
				cmd += b + "; ";
			}
			info("Recieved Draw Call: " + cmd);
		} catch (IOException e) {
			fatal(e);
		} catch (InterruptedException e) {
			fatal(e);
		}
		finally {
			try {
				if (socket != null) socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				fatal(e);
			}
		}
	}
}
