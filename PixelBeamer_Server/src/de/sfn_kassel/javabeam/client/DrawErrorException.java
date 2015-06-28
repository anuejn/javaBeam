package de.sfn_kassel.javabeam.client;

public class DrawErrorException extends Exception {

	private static final long serialVersionUID = -3981212706123922263L;

	public DrawErrorException() {
		super();
	}

	public DrawErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DrawErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DrawErrorException(String message) {
		super(message);
	}

	public DrawErrorException(Throwable cause) {
		super(cause);
	}
	
	
}
