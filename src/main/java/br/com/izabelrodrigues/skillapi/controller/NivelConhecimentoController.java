package br.com.izabelrodrigues.skillapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.skillapi.exception.CustomExceptionHandler;
import br.com.izabelrodrigues.skillapi.service.INivelConhecimentoService;

@RestControllerAdvice
@RequestMapping("/knowledge")
public class NivelConhecimentoController extends CustomExceptionHandler {

	@Autowired
	private INivelConhecimentoService service;

	@GetMapping("list")
	public List<String> findAll() {

		return service.findAll();
	}

}
