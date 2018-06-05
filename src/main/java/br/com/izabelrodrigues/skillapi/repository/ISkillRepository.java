package br.com.izabelrodrigues.skillapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.izabelrodrigues.skillapi.model.Skill;

@Repository
public interface ISkillRepository extends IGenericRepository<Skill> {
	
	@Query("select s from Skill s where s.descricao like %:descricao%")
	public List<Skill> findByDescricaoContaining(String descricao);

}
