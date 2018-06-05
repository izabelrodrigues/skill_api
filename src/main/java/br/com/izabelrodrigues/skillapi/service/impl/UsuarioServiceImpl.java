package br.com.izabelrodrigues.skillapi.service.impl;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.repository.IUsuarioRepository;
import br.com.izabelrodrigues.skillapi.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository repository;

	@Override
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public ResponseEntity<?> saveOrUpdate(Usuario entity) {
		Usuario userCreated = repository.save(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{id}")
				.buildAndExpand(userCreated.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}


}
