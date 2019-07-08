package br.com.izabelrodrigues.skill.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.izabelrodrigues.skill.api.model.Usuario;

/**
 * Repositório para operações da entidade Usuario
 * @author Izabel Rodrigues
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/**
	 * Busca um usuário pelo e-mail
	 * @param email
	 * @return
	 */
	Optional<Usuario> findByEmail(String email);

}
