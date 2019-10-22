package br.com.fernandoevangelista.exception;

public class CarException extends Exception {

	private static final long serialVersionUID = -90745172780975718L;
	
	public CarException(String message, Throwable cause) {
		super(message, cause);
	}

	public CarException(String message) {
		super(message);
	}

	public CarException(Throwable cause) {
		super(cause);
	}

}
