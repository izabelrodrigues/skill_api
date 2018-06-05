package br.com.izabelrodrigues.skillapi.controller;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.skillapi.component.Mensagem;
import br.com.izabelrodrigues.skillapi.exception.CustomExceptionHandler;
import br.com.izabelrodrigues.skillapi.exception.NotFoundException;
import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.model.UsuarioSkill;
import br.com.izabelrodrigues.skillapi.service.IUsuarioService;
import br.com.izabelrodrigues.skillapi.service.IUsuarioSkillService;
import br.com.izabelrodrigues.skillapi.vo.UsuarioSkillVO;

@RestControllerAdvice
@RequestMapping("/user-skill")
public class UsuarioSkillController extends CustomExceptionHandler {

	@Autowired
	private IUsuarioSkillService service;

	@Autowired
	private IUsuarioService userService;

	@Autowired
	private Mensagem mensagem;

	@GetMapping("list")
	public Iterable<UsuarioSkill> findAll() {

		return service.findAll();
	}

	@GetMapping("user/{id}")
	public List<UsuarioSkillVO> findByUsuario(@PathVariable Long id) {

		String userNotFound = mensagem.getString("user.not-found");
		Usuario usuario = userService.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format(userNotFound, id)));

		return service.findByUsuario(usuario);
	}
}
