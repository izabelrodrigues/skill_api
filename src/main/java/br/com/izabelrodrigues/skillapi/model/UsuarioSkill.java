package br.com.izabelrodrigues.skillapi.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.izabelrodrigues.skillapi.enums.NivelConhecimentoConverter;
import br.com.izabelrodrigues.skillapi.enums.NivelConhecimentoEnum;

@Entity
@Table(name="USUARIO_SKILL")
public class UsuarioSkill {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Skill skill;

	@ManyToOne()
    @JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	@Convert(converter = NivelConhecimentoConverter.class)
	@Column(name="NIVEL_CONHECIMENTO")
	private NivelConhecimentoEnum nivelConhecimento;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
