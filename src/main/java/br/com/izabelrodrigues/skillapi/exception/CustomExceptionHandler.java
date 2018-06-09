package br.com.izabelrodrigues.skillapi.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.hateoas.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.izabelrodrigues.skillapi.component.Mensagem;

public class CustomExceptionHandler {

	@Autowired
	private Mensagem mensagem;

	@Autowired
	private Validator validator;

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors notFoundHandler(NotFoundException ex) {
		VndErrors vndErrors = new VndErrors("message", ex.getMessage());
		vndErrors.add(new VndError("http_status", HttpStatus.NOT_FOUND.toString()));
		return vndErrors;
	}

	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	VndErrors constraintErrorHandler(ConstraintViolationException ex) {
		VndErrors vndErrors = new VndErrors("message", ex.getMessage());
		vndErrors.add(new VndError("http_status", HttpStatus.BAD_REQUEST.toString()));
		return vndErrors;
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	VndErrors internalServerErrorHandler(Exception ex) {
		VndErrors vndErrors = new VndErrors("message", ex.getMessage());
		vndErrors.add(new VndError("http_status", HttpStatus.INTERNAL_SERVER_ERROR.toString()));
		return vndErrors;
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	VndErrors invalidArgurmentHandler(MethodArgumentNotValidException ex) {
		VndErrors vndErrors = new VndErrors("message", ex.getMessage());
		vndErrors.add(new VndError("http_status", HttpStatus.BAD_REQUEST.toString()));
		return vndErrors;
	}

	/**
	 * Recupera a lista de erros ocasionados pelo Validate.
	 * Os erros são de acordo com o idioma informado na requisição.
	 * @param obj - Entidade a ser validada
	 * @param idioma - Idioma do usuário recebido na requisição
	 * @return
	 */
	protected String recuperaListaDeErros(Object obj,String idioma) {

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);

		List<String> errorMessages = new ArrayList<>();

		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			errorMessages.add(mensagem.getString(constraintViolation.getMessage(), idioma));
		}
		return errorMessages.toString();

	}

}
