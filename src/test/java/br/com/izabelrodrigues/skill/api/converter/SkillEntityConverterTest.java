package br.com.izabelrodrigues.skill.api.converter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.izabelrodrigues.skill.api.SkillApiApplication;
import br.com.izabelrodrigues.skill.api.SkillApiPrepareTest;
import br.com.izabelrodrigues.skill.api.dto.SkillDto;
import br.com.izabelrodrigues.skill.api.model.Skill;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SkillEntityConverterTest {

	@Test
	public void quandoForPassadoUmPageSkillDeveRetornarPageSKillDto() {
		Page<Skill> pageSkill = SkillApiPrepareTest.getPageSkill();
		Page<SkillDto> pageDto = SkillEntityConverter.convertToPageDto(pageSkill);

		assertTrue("O conteudo da Page de DTO deveria ter 3 elementos", pageDto.getNumberOfElements() == 3);

		SkillDto skillDto = pageDto.getContent().get(0);
		assertTrue("O primeiro elemento do conteudo deveria ser do tipo SKillDto", skillDto instanceof SkillDto);
	}

}
