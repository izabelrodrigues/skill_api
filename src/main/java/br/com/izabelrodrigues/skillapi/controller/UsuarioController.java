package br.com.izabelrodrigues.skillapi.controller;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.service.IUsuarioService;

/**
 * Cpntroller para tratar as requisições para a entidade Usuario
 * @author Izabel Rodrigues
 *
 */
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
	public Usuario findById(@PathVariable Long id, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {
		String userNotFound = mensagem.getString("user.not-found", idioma);
		return service.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format(userNotFound, id)));
	}

	@PostMapping(path = "/create", produces = "application/json")
	public ResponseEntity<Object> save(@RequestBody Usuario user, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma){

		try {
			Optional<Usuario> createUser = service.saveOrUpdate(user);
			if (createUser.isPresent()) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{id}")
						.buildAndExpand(createUser.get().getId()).toUri();
				return ResponseEntity.created(location).build();
			} else {
				String msg = mensagem.getString("user.duplicated", idioma);
				throw new DuplicatedViolationException(MessageFormat.format(msg, user.getNome()));
			}
		} catch (Exception e) {
			String errors = recuperaListaDeErros(user, idioma);
			if(StringUtils.isNotBlank(errors)) {
				throw new ApplicationBusinessException(errors);
			}else {
				throw new ApplicationBusinessException(e.getMessage());
			}

		}
	}

	@PutMapping(path = "/update", consumes = "application/json")
	public ResponseEntity<Object> update(@RequestBody Usuario usuario, @RequestHeader(name=Constants.ACCEPT_LANGUAGE) String idioma) {
		try {
			Optional<Usuario> entity = service.saveOrUpdate(usuario);
			if (entity.isPresent()) {
				return ResponseEntity.ok().build();
			}
			else {
				String msg = mensagem.getString("generic.error.action", idioma);
				String action = mensagem.getString("action.update", idioma);
				throw new ApplicationBusinessException(MessageFormat.format(msg, action));
			}
		} catch (ConstraintViolationException c) {

			throw new ApplicationBusinessException("licensePlate" );

		}
	}



}
