package br.com.izabelrodrigues.skill.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.izabelrodrigues.skill.api.converter.SkillEntityConverter;
import br.com.izabelrodrigues.skill.api.dto.SkillDto;
import br.com.izabelrodrigues.skill.api.model.Skill;
import br.com.izabelrodrigues.skill.api.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	/**
	 * 
	 * @param pageable
	 * @param filtro
	 * @return
	 */
	public Page<SkillDto> listar(Pageable pageable, String filtro) {

		if (null == filtro) {
			return SkillEntityConverter.convertToPageDto(skillRepository.findAll(pageable));
		}

		return SkillEntityConverter.convertToPageDto(skillRepository.findByNome(pageable, filtro));
	}

	/**
	 * @param skill
	 */
	public void salvar(Skill skill) {
		skillRepository.save(skill);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Skill> buscarPorId(Long id) {
		return skillRepository.findById(id);
	}

	public void atualizar(Skill skill) {
		salvar(skill);
	}

	public void remover(Long id) {
		skillRepository.deleteById(id);
	}

}
