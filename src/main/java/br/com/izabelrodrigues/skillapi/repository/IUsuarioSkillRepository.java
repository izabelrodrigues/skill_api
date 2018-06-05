package br.com.izabelrodrigues.skillapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.izabelrodrigues.skillapi.model.Usuario;
import br.com.izabelrodrigues.skillapi.model.UsuarioSkill;
import br.com.izabelrodrigues.skillapi.vo.UsuarioSkillVO;

@Repository
public interface IUsuarioSkillRepository extends IGenericRepository<UsuarioSkill>{

	@Query("select new br.com.izabelrodrigues.skillapi.vo.UsuarioSkillVO(u.nome, s, us.nivelConhecimento) from UsuarioSkill as us join us.skill as s join us.usuario as u where us.usuario = :usuario")
	List<UsuarioSkillVO> findByUsuario(@Param("usuario") Usuario usuario);


}
