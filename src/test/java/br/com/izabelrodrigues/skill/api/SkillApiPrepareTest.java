/**
 *
 */
package br.com.izabelrodrigues.skill.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.core.env.Environment;

/**
 * @author Izabel Rodrigues
 *
 */
public class SkillApiPrepareTest {

	@Test
	public void getPortTestEnviromentNull() {
		assertTrue(getPortTest(null).equals(""));

	}

	public static String getPortTest(Environment environment) {

		if (null != environment) {
			return environment.getProperty("local.server.port");
		}
		return "";
	}

}
