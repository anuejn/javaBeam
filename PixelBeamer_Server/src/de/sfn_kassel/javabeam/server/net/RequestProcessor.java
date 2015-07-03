package de.sfn_kassel.javabeam.server.net;

import static de.sfn_kassel.javabeam.server.Main.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import de.sfn_kassel.javabeam.util.ArrayUtil;


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
			ArrayList<Byte> bigBytes = new ArrayList<>();
			
			BufferedInputStream bin = new BufferedInputStream(in);
			while (true) {
				int temp = bin.read();
				if(temp < 0)
					break;
				bigBytes.add((byte)temp);
			}
			out.write(new byte[]{(byte) 0xAA}, 0, 1);
			out.flush();
			
			Byte[] bytes = new Byte[bigBytes.size()];
			bigBytes.toArray(bytes);
			
			synchronized (handler.commands) {
				handler.commands.add(ArrayUtil.deepCopyArray(bytes));
			}
			
			String cmd = "";
			for (byte b : bytes) {
				cmd += b + "; ";
			}
			info("Recieved Draw Call: " + cmd);
		} catch (IOException e) {
//			fatal(e);
//		} catch (InterruptedException e) {
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
