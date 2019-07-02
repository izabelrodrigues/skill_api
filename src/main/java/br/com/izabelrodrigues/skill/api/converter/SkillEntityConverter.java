package br.com.izabelrodrigues.skill.api.converter;

import org.springframework.data.domain.Page;

import br.com.izabelrodrigues.skill.api.dto.SkillDto;
import br.com.izabelrodrigues.skill.api.model.Skill;

public class SkillEntityConverter {
	
	
	private SkillEntityConverter() {}
	
	/**
	 * Converte um Page da entidade Skill em um Page do dto de Skill
	 * 
	 * @param page
	 * @return
	 */
	public static Page<SkillDto> convertToPageDto(Page<Skill> page) {
		return page.map(SkillDto::new);
	}
	
	/**
	 * Converte uma SkillDto para uma Skill
	 * @param skillDto
	 * @return
	 */
	public static Skill convertFromDto(SkillDto skillDto) {
		return new Skill(skillDto);
	}
	
	/**
	 * Converte uma Skill para SkillDto
	 * @param skill
	 * @return
	 */
	public static SkillDto convertToDto(Skill skill) {
		return new SkillDto(skill);
	}

}
