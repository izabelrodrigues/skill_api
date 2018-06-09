package br.com.izabelrodrigues.skillapi.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.izabelrodrigues.skillapi.model.Skill;
import br.com.izabelrodrigues.skillapi.repository.ISkillRepository;
import br.com.izabelrodrigues.skillapi.service.ISkillService;

@Service
public class SkillServiceImpl implements ISkillService {

	@Autowired
	private ISkillRepository repository;

	@Override
	public Optional<Skill> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<Skill> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Skill> saveOrUpdate(@Valid Skill entity) {

		entity.setDescricao(entity.getDescricao().toUpperCase());
		boolean isNew = (null == entity.getId());
		return (isNew ? Optional.ofNullable(save(entity)) : Optional.ofNullable(update(entity)));

	}

	@Override
	public Skill save(Skill entity) {

		if (!existsSkill(entity)) {
			return repository.save(entity);
		}

		return null;

	}

	@Override
	public Skill update(Skill entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	private boolean existsSkill(Skill entity) {
		return (null != findByDescricao(entity.getDescricao()));
	}

	@Override
	public Skill findByDescricao(String descricao) {
		return repository.findByDescricao(descricao);
	}

}
