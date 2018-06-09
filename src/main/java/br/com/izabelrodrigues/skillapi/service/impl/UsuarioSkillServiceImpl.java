package br.com.izabelrodrigues.skillapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Optional <UsuarioSkill> saveOrUpdate(UsuarioSkill entity) {
		boolean isNew = (null == entity.getId());
		return (isNew ? Optional.ofNullable(save(entity)) : Optional.ofNullable(update(entity)));

	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);

	}

	@Override
	public List<UsuarioSkillVO> findByUsuario(Usuario usuario) {

		return repository.findByUsuario(usuario);
	}

	@Override
	public UsuarioSkill save(UsuarioSkill entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioSkill update(UsuarioSkill entity) {
		// TODO Auto-generated method stub
		return null;
	}



}
