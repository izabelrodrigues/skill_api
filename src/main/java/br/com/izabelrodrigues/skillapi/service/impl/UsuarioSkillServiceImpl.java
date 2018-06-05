package br.com.izabelrodrigues.skillapi.service.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.model.UsuarioSkill;
import br.com.izabelrodrigues.skillapi.repository.IUsuarioSkillRepository;
import br.com.izabelrodrigues.skillapi.service.IUsuarioSkillService;
import br.com.izabelrodrigues.skillapi.vo.UsuarioSkillVO;

@Service
public class UsuarioSkillServiceImpl implements IUsuarioSkillService {

	@Autowired
	private IUsuarioSkillRepository repository;

	@Override
	public Optional<UsuarioSkill> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<UsuarioSkill> findAll() {
		return repository.findAll();
	}

	@Override
	public ResponseEntity<?> saveOrUpdate(UsuarioSkill entity) {
		boolean isNew = (null == entity.getId());


		UsuarioSkill usuarioSkill = repository.save(entity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/skill/{id}")
				.buildAndExpand(usuarioSkill.getId()).toUri();

		return( isNew ? ResponseEntity.created(location).body(usuarioSkill) : ResponseEntity.ok().build());

	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);

	}

	@Override
	public List<UsuarioSkillVO> findByUsuario(Usuario usuario) {

		return repository.findByUsuario(usuario);
	}

//	@Override
//	public List<UsuarioSkill> findByUsuario(Long usuarioId) {
//		return repository.findqualquercoisa(usuarioId);
//	}

}
