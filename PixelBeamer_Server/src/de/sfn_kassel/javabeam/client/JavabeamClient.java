package de.sfn_kassel.javabeam.client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import de.sfn_kassel.javabeam.util.Drawable;

public class JavabeamClient {
	private String ip;

	/**
	 * 
	 * @param ip Die IP-Adresse des Zielcomputers
	 */
	public JavabeamClient(String ip) {
		this.ip = ip;
	}

	public void sendToServer(Drawable object) throws DrawErrorException {
		Socket connection = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			connection = new Socket(ip, 8088);
			oos = new ObjectOutputStream(connection.getOutputStream());
			ois = new ObjectInputStream(connection.getInputStream());
			oos.writeObject(object);
			Object o;
			try {
				while ((o = ois.readObject()) != null) {
					if (o instanceof Throwable) {
						connection.close();
						throw new DrawErrorException((Throwable) o);
					}
				}
			} catch (EOFException e) {}
		} catch (IOException | ClassNotFoundException e) {
			throw new DrawErrorException(e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
