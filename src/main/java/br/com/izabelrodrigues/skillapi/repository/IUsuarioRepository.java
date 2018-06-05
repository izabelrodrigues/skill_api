package br.com.izabelrodrigues.skillapi.repository;

import org.springframework.stereotype.Repository;

import br.com.izabelrodrigues.skillapi.model.Usuario;

@Repository
public interface IUsuarioRepository extends IGenericRepository<Usuario> {
	
	
	//public List<Usuario> findUsuariosBySkillId(Long idSkill);

}
