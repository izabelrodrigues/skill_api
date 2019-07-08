package br.com.izabelrodrigues.skill.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {

	@NotNull
	@Size(min = 3, max = 150)
	private String email;

	@NotNull
	@Size(min = 6)
	private String senha;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Converte os dados do objeto login passado na requisição para um
	 * UsernamePasswordAuthenticationToken
	 *
	 * @param login
	 * @return
	 */
	public UsernamePasswordAuthenticationToken convertToAuthentication(@Valid LoginDto login) {
		return new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
	}

}
