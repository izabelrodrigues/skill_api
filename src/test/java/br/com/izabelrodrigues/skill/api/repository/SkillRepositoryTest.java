package br.com.izabelrodrigues.skill.api.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.izabelrodrigues.skill.api.ResetDatabaseTestExecutionListener;
import br.com.izabelrodrigues.skill.api.SkillApiApplication;
import br.com.izabelrodrigues.skill.api.SkillApiPrepareTest;
import br.com.izabelrodrigues.skill.api.model.Skill;

@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS, listeners = {
		ResetDatabaseTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SkillRepositoryTest {

	private static final String SKILL_DESC_REP1 = "DESC REP1";
	private static final String SKILL_NOME_TESTE_REP1 = "TESTE REP1";
	private static final String NAO_ESTOU_NO_BANCO = "NAO_ESTOU_NO_BANCO";

	@Autowired
	SkillRepository skillRepository;

	@Before
	public void initDatabase() {

		skillRepository.saveAll(SkillApiPrepareTest.getListaInicialSkill());
	}

	@Test
	public void contextLoads() {
		Assert.assertNotNull("A instancia de skillRepository não deveria ser nula.", this.skillRepository);
	}

	@Test
	public void quandoSkillValidaEntaoSalvaNoBancoDados() {

		Skill skill = new Skill(null, "TESTE REP4", "DESC REP4", true);
		Skill novaSkill = skillRepository.save(skill);

		Long idSkillSalva = novaSkill.getId();
		Assert.assertNotNull("O id da Skill não deveria estar nulo.", idSkillSalva);

		Assert.assertTrue(4L == idSkillSalva);

	}

	@Test
	public void quandoBuscaSkillComNomeREP1entaoDescricaDeveriaSerDESCREP1AndId1() {

		Page<Skill> skillByNome = skillRepository.findByNome(null, SKILL_NOME_TESTE_REP1);
		List<Skill> content = skillByNome.getContent();
		assertTrue("A lista não deveria estar vazia", !content.isEmpty());
		assertTrue("A lista deveria ter apenas 1 elemento", content.size() == 1);
		Skill skill = content.get(0);
		Assert.assertEquals("A descrição deveria ser DESC REP1", SKILL_DESC_REP1, skill.getDescricao());
		Assert.assertEquals("O id deveria ser 1", new Long(1), skill.getId());

	}

	@Test
	public void quandoBuscaSkillInexistenteConsultaDeveriaRetornarZeroElementos() {

		Page<Skill> skillByNome = skillRepository.findByNome(null, NAO_ESTOU_NO_BANCO);
		List<Skill> content = skillByNome.getContent();
		assertTrue("A lista não deveria ter elementos", content.isEmpty());
	}

	@Test
	public void quandoBuscaSkillPorIdComIdExistenteDeveRetornarASkillDoBanco() {
		Optional<Skill> skillById = skillRepository.findById(1L);

		boolean skillEncontrada = skillById.isPresent();

		assertTrue("Deveria existir Skill com ID 1", skillEncontrada == true);
	}

}
