package br.com.izabelrodrigues.skillapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.skillapi.Constants;
import br.com.izabelrodrigues.skillapi.exception.CustomExceptionHandler;
import br.com.izabelrodrigues.skillapi.service.INivelConhecimentoService;

/**
 * Controller para os níveis de conhecimento de uma Skill
 * @author Izabel Rodrigues
 *
 */
@RestControllerAdvice
@RequestMapping("/knowledge")
public class NivelConhecimentoController extends CustomExceptionHandler {


	@Autowired
	private INivelConhecimentoService service;

	/**
	 * Lista todos os níveis de conhecimento
	 * @return
	 */
	@GetMapping("list")
	public List<String> findAll(@RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {

		return service.findAll(idioma);
	}

}
