package br.com.izabelrodrigues.skill.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.izabelrodrigues.skill.api.Constants;

@Entity
public class Usuario implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7319859712704341417L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = Constants.USUARIO_SEQUENCE)
	private Long id;

	@NotBlank
	private String nome;
	
	@NotNull
	@Size(min = 3, max = 150)
	private String email;
	
	@Column(columnDefinition = "boolean default true", nullable = false)
	private Boolean ativo;
	
	@NotNull
	@Size(min=6)
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	/**
	 * 
	 */
	public Usuario() {
	}

	/**
	 * @param id
	 * @param nome
	 * @param email
	 * @param ativo
	 * @param senha
	 * @param perfis
	 */
	public Usuario(Long id, @NotBlank String nome, @NotNull @Size(min = 3, max = 150) String email, Boolean ativo,
			@Size(min = 6) String senha, List<Perfil> perfis) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.ativo = ativo;
		this.senha = senha;
		this.perfis = perfis;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getPerfis();
	}

	@Override
	public String getPassword() {
		return getSenha();
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, email, id, nome, perfis, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(ativo, other.ativo) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(perfis, other.perfis)
				&& Objects.equals(senha, other.senha);
	}

}
