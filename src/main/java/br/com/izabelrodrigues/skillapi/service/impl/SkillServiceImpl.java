package br.com.izabelrodrigues.skillapi.service.impl;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.izabelrodrigues.skillapi.component.Mensagem;
import br.com.izabelrodrigues.skillapi.exception.DuplicatedViolationException;
import br.com.izabelrodrigues.skillapi.model.Skill;
import br.com.izabelrodrigues.skillapi.repository.ISkillRepository;
import br.com.izabelrodrigues.skillapi.service.ISkillService;

@Service
public class SkillServiceImpl implements ISkillService {

	@Autowired
	private ISkillRepository repository;

	@Autowired
	private Mensagem mensagem;

	@Override
	public Optional<Skill> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<Skill> findAll() {
		return repository.findAll();
	}

	@Override
	public ResponseEntity<?> saveOrUpdate(Skill entity) {

		entity.setDescricao(entity.getDescricao().toUpperCase());
		boolean isNew = (null == entity.getId());
		return (isNew ? save(entity) : update(entity));

	}

	private ResponseEntity<?> save(Skill entity) {
		Skill skill = null;

		try {
			skill = repository.save(entity);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/skill/{id}")
					.buildAndExpand(skill.getId()).toUri();

			return ResponseEntity.created(location).body(skill);

		} catch (Exception e) {
			String skillDuplicada = mensagem.getString("skill.duplicated");
			return new ResponseEntity<>(
					new DuplicatedViolationException(MessageFormat.format(skillDuplicada, entity.getDescricao())),
					HttpStatus.CONFLICT);
		}

	}

	private ResponseEntity<?> update(Skill entity) {
		repository.save(entity);
		return ResponseEntity.ok().build();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
