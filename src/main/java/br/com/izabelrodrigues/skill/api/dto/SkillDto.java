package br.com.izabelrodrigues.skill.api.dto;

import br.com.izabelrodrigues.skill.api.dto.abstracts.AbstractSkill;
import br.com.izabelrodrigues.skill.api.model.Skill;

/**
 * Classe auxiliar para ser usada nos retornos da API
 *
 * @author Izabel Rodrigues
 *
 */
public class SkillDto extends AbstractSkill {

	public SkillDto() {
		this(new Skill());// squid:S2637
	}

	/**
	 * @param id
	 * @param nome
	 * @param descricao
	 * @param ativo
	 */
	public SkillDto(Skill skill) {
		this.id = skill.getId();
		this.nome = skill.getNome();
		this.descricao = skill.getDescricao();
		this.ativo = skill.getAtivo();
	}

}
