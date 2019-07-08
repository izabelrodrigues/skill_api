package br.com.izabelrodrigues.skill.api.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.izabelrodrigues.skill.api.SkillApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SkillApiUtilTest {

	@Test
	public void quandoObjetoForNuloEntaoDeveRetornarStringComTextonull() {

		String json = SkillApiUtil.asJsonString(null);
		Assert.assertEquals("O JSON deveria ser a string null", "null", json);

	}

}
