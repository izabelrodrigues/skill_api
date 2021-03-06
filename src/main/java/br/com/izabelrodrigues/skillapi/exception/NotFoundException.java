package br.com.izabelrodrigues.skillapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7359943354165561524L;

	public NotFoundException(String msgNotFound) {
		super(msgNotFound);
	}
	

}
