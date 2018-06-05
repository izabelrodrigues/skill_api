package br.com.izabelrodrigues.skillapi.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.skillapi.component.Mensagem;
import br.com.izabelrodrigues.skillapi.exception.CustomExceptionHandler;
import br.com.izabelrodrigues.skillapi.exception.NotFoundException;
import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.service.IUsuarioService;

@RestControllerAdvice
@RequestMapping("/user")
public class UsuarioController extends CustomExceptionHandler {

	@Autowired
	private Mensagem mensagem;

	@Autowired
	private IUsuarioService service;

	@GetMapping("list")
	public Iterable<Usuario> findAll() {

		return service.findAll();
	}
	@GetMapping("/{id}")
	public Usuario findById(@PathVariable Long id) {
		String userNotFound = mensagem.getString("user.not-found");
		return service.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format(userNotFound, id)));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Usuario user){
		return service.saveOrUpdate(user);
	}



}
