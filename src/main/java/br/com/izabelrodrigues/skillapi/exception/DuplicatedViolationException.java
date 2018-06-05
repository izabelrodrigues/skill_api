package br.com.izabelrodrigues.skillapi.exception;

public class DuplicatedViolationException  extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 7359943354165561524L;

	public DuplicatedViolationException(String msg) {
		super(msg);
	}


}
