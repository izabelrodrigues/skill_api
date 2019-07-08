/**
 *
 */
package br.com.izabelrodrigues.skill.api;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.izabelrodrigues.skill.api.model.Skill;

/**
 * @author Izabel Rodrigues
 *
 */
public class SkillApiPrepareTest {

	@Test
	public void getPortTestEnviromentNull() {
		assertTrue(getPortTest(null).equals(""));

	}

	public static List<Skill> getListaInicialSkill() {

		Skill skill_1 = new Skill(null, "TESTE REP1", "DESC REP1", true);
		Skill skill_2 = new Skill(null, "TESTE REP2", "DESC REP2", true);
		Skill skill_3 = new Skill(null, "TESTE REP3", "DESC REP3", true);

		List<Skill> lista = new ArrayList<>();
		lista.add(skill_1);
		lista.add(skill_2);
		lista.add(skill_3);

		return lista;

	}

	public static String getPortTest(Environment environment) {

		if (null != environment) {
			return environment.getProperty("local.server.port");
		}
		return "";
	}

	public static Page<Skill> getPageSkill() {
		return new PageImpl<>(getListaInicialSkill());
	}

}
