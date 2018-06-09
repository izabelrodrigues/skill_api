package br.com.izabelrodrigues.skillapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.izabelrodrigues.skillapi.model.Usuario;

@Repository
public interface IUsuarioRepository extends IGenericRepository<Usuario> {

	@Query("select u from Usuario u where u.nome = :nome or u.email = :email")
	Optional<Usuario> findByNomeOrEmail(@Param("nome") String nome, @Param("email") String email);

}
