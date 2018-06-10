package br.com.izabelrodrigues.skillapi.controller;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.izabelrodrigues.skillapi.Constants;
import br.com.izabelrodrigues.skillapi.component.Mensagem;
import br.com.izabelrodrigues.skillapi.exception.ApplicationBusinessException;
import br.com.izabelrodrigues.skillapi.exception.CustomExceptionHandler;
import br.com.izabelrodrigues.skillapi.exception.DuplicatedViolationException;
import br.com.izabelrodrigues.skillapi.exception.NotFoundException;
import br.com.izabelrodrigues.skillapi.model.Skill;
import br.com.izabelrodrigues.skillapi.service.ISkillService;

/**
 * Controller para a entidade Skill
 * @author Izabel Rodrigues
 *
 */
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
	public Skill findById(@PathVariable Long id, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {
		String skillNotFound = mensagem.getString("skill.not-found", idioma);
		return service.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format(skillNotFound, id)));
	}

	@PutMapping(path = "/update", consumes = "application/json")
	public ResponseEntity<Object> update(@RequestBody Skill skill, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {
		try {
			Optional<Skill> entity = service.saveOrUpdate(skill);
			if (entity.isPresent()) {
				return ResponseEntity.ok().build();
			}
			else {
				String msg = mensagem.getString(Constants.GENERIC_ERROR_ACTION, idioma);
				String action = mensagem.getString("action.update", idioma);
				throw new ApplicationBusinessException(MessageFormat.format(msg, action));
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),	HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path = "/create", produces = "application/json")
	public ResponseEntity<Object> save(@RequestBody Skill skill, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {


		try {
			Optional<Skill> entity = service.saveOrUpdate(skill);
			if(entity.isPresent()) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/skill/{id}")
						.buildAndExpand(skill.getId()).toUri();

				return ResponseEntity.created(location).body(entity);
			}else {
				String skillDuplicada = mensagem.getString("skill.descricao.unique", idioma);
				throw new DuplicatedViolationException(MessageFormat.format(skillDuplicada, skill.getDescricao()));
			}

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(),	HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {

		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			String action = mensagem.getString("action.delete", idioma);
			String msg = MessageFormat.format(mensagem.getString(Constants.GENERIC_ERROR_ACTION, idioma), action);
			throw new ApplicationBusinessException(msg);
		}
	}


}
