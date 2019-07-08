package br.com.izabelrodrigues.skill.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.izabelrodrigues.skill.api.model.Skill;

/**
 * Repositório para operações da entidade Skill
 * @author Izabel Rodrigues
 *
 */
public interface SkillRepository extends JpaRepository<Skill, Long>{

	Page<Skill> findByNome(Pageable pageable, String nome);

}
