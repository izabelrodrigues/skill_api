package br.com.izabelrodrigues.skill.api.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.izabelrodrigues.skill.api.model.Skill;

/**
 * Classe auxiliar para ser usada nos retornos da API
 * 
 * @author Izabel Rodrigues
 *
 */
public class SkillDto {

	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@NotNull
	@Size(min = 3, max = 500)
	private String descricao;
	
	@NotNull
	private Boolean ativo;

	public SkillDto() {
		this(new Skill());//squid:S2637
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

}
