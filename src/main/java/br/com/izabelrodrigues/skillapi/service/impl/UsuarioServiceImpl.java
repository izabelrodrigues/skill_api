package br.com.izabelrodrigues.skillapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Optional<Usuario> saveOrUpdate(Usuario entity) {
		entity.setNome(entity.getNome().toUpperCase());
		entity.setEmail(entity.getEmail().toUpperCase());
		boolean isNew = (null == entity.getId());
		return (isNew ? Optional.ofNullable(save(entity)) : Optional.ofNullable(update(entity)));


	}


	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	private boolean existsUsuario(Usuario usuario) {
		return repository.findByNomeOrEmail(usuario.getNome(), usuario.getEmail()).isPresent();
	}

	@Override
	public Usuario save(Usuario entity) {
		if(!existsUsuario(entity)) {
			return repository.save(entity);
		}
		return null;
	}

	@Override
	public Usuario update(Usuario entity) {
		return repository.save(entity);
	}


}
