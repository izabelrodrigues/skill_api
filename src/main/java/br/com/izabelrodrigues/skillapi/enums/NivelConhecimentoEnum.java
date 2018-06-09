/**
 *
 */
package br.com.izabelrodrigues.skillapi.enums;

/**
 * Enum para definição dos níveis de conhecimento de uma Skill
 * @author Izabel Rodrigues
 *
 */
public enum NivelConhecimentoEnum {

	NONE(0L, "nivel.nenhum.descricao","nivel.nenhum.nome"),
	THEORY(1L, "nivel.teoria.descricao", "nivel.teoria.nome"),
	BASIC(2L, "nivel.basico.descricao","nivel.basico.nome"),
	INTERMEDIARY(3L, "nivel.intermediario.descricao", "nivel.intermediario.nome"),
	EXPERT(4L, "nivel.avancado.descricao", "nivel.avancado.nome");

	private Long value;
	private String descricao;
	private String nomeTipo;

	private NivelConhecimentoEnum(Long value, String descricao, String nomeTipo) {

		this.value = value;
		this.descricao = descricao;
		this.nomeTipo = nomeTipo;
	}

	/**
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the nomeTipo
	 */
	public String getNomeTipo() {
		return  nomeTipo;
	}

}
