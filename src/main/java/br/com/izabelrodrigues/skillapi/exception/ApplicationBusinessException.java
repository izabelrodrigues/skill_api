package br.com.izabelrodrigues.skillapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationBusinessException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 8905499266271823484L;

	public ApplicationBusinessException(String msg) {
		super(msg);
	}

}
