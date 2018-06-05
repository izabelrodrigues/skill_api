package br.com.izabelrodrigues.skillapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {
	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	private String observacao;


	/**
	 *
	 */
	public Skill() {
		super();
	}

	/**
	 * @param id
	 * @param descricao
	 * @param observacao
	 */
	public Skill(Long id, String descricao, String observacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.observacao = observacao;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao
	 *            the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


}