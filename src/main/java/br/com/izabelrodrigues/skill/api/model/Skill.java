package br.com.izabelrodrigues.skill.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.izabelrodrigues.skill.api.Constants;
import br.com.izabelrodrigues.skill.api.dto.SkillDto;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constants.SKILL_SEQUENCE)
	private Long id;

	@NotBlank
	private String nome;
	
	@NotNull
	@Size(min = 3, max = 500)
	private String descricao;
	
	@Column(columnDefinition = "boolean default true", nullable = false)
	private Boolean ativo;

	/**
	 * 
	 */
	public Skill() {
	}

	/**
	 * @param id
	 * @param nome
	 * @param descricao
	 * @param ativo
	 */
	public Skill(Long id, String nome, String descricao, Boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	
	/**
	 * 
	 * @param skillDto
	 */
	public Skill(SkillDto skillDto) {
		this.id = skillDto.getId();
		this.nome = skillDto.getNome();
		this.descricao = skillDto.getDescricao();
		this.ativo = skillDto.getAtivo();
	}

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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, descricao, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(ativo, other.ativo) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

}
