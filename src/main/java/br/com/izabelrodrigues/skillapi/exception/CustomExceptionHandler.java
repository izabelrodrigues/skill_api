package br.com.izabelrodrigues.skillapi.exception;

import org.springframework.hateoas.VndErrors;
import org.springframework.hateoas.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptionHandler {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors notFoundHandler(NotFoundException ex) {
		VndErrors vndErrors = new VndErrors("mensagem", ex.getMessage());
		vndErrors.add(new VndError("httpStatusCode", HttpStatus.NOT_FOUND.toString()));
		return vndErrors;
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	VndErrors internalServerErrorHandler(Exception ex) {
		VndErrors vndErrors = new VndErrors("mensagem", ex.getMessage());
		vndErrors.add(new VndError("httpStatusCode", HttpStatus.INTERNAL_SERVER_ERROR.toString()));
		return vndErrors;
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	VndErrors invalidArgurmentHandler(MethodArgumentNotValidException ex) {
		VndErrors vndErrors = new VndErrors("mensagem", ex.getMessage());
		vndErrors.add(new VndError("httpStatusCode", HttpStatus.BAD_REQUEST.toString()));
		return vndErrors;
	}

}
