package br.com.izabelrodrigues.skillapi.exception;

public class ApplicationBusinessException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 8905499266271823484L;

	public ApplicationBusinessException(String msg) {
		super(msg);
	}

}
