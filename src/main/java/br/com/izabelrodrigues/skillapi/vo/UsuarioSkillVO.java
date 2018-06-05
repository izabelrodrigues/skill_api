package br.com.izabelrodrigues.skillapi.vo;

import br.com.izabelrodrigues.skillapi.enums.NivelConhecimentoEnum;
import br.com.izabelrodrigues.skillapi.model.Skill;

public class UsuarioSkillVO {

	private String usuario;

	private Skill skill;

	private NivelConhecimentoEnum nivelConhecimento;

	/**
	 * @param usuario
	 * @param idSkill
	 * @param descricao
	 * @param nivelConhecimento
	 */
	public UsuarioSkillVO(String usuario, Skill skill,  NivelConhecimentoEnum nivelConhecimento) {
		this.usuario = usuario;
		this.skill = skill;
		this.nivelConhecimento = nivelConhecimento;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * @return the nivelConhecimento
	 */
	public NivelConhecimentoEnum getNivelConhecimento() {
		return nivelConhecimento;
	}

	/**
	 * @param nivelConhecimento the nivelConhecimento to set
	 */
	public void setNivelConhecimento(NivelConhecimentoEnum nivelConhecimento) {
		this.nivelConhecimento = nivelConhecimento;
	}

}
