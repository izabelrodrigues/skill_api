package br.com.izabelrodrigues.skillapi.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.skillapi.component.Mensagem;
import br.com.izabelrodrigues.skillapi.exception.CustomExceptionHandler;
import br.com.izabelrodrigues.skillapi.exception.NotFoundException;
import br.com.izabelrodrigues.skillapi.model.Skill;
import br.com.izabelrodrigues.skillapi.service.ISkillService;

@RestControllerAdvice
@RequestMapping("/skill")
public class SkillController extends CustomExceptionHandler {

	@Autowired
	private ISkillService service;

	@Autowired
	private Mensagem mensagem;

	@GetMapping("list")
	public Iterable<Skill> findAll() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public Skill findById(@PathVariable Long id) {
		String skillNotFound = mensagem.getString("skill.not-found");
		return service.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format(skillNotFound, id)));
	}

	@PutMapping(path = "/update", consumes = "application/json")
	public ResponseEntity<?> update(@RequestBody Skill skill) {
		return service.saveOrUpdate(skill);
	}

	@PostMapping(path="/create",produces = "application/json")
	public ResponseEntity<?> save(@RequestBody Skill skill) {
		return service.saveOrUpdate(skill);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
