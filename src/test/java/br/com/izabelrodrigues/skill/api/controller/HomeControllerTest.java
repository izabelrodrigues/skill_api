package br.com.izabelrodrigues.skill.api.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.izabelrodrigues.skill.api.SkillApiApplication;
import br.com.izabelrodrigues.skill.api.SkillApiPrepareTest;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HomeControllerTest {

	@InjectMocks
	HomeController homeController;

	@Autowired
	Environment environment;

	@Test
	public void dadoQueUsuarioNaoAutenticadoQuandoAcessarHomeEntaoNaoAutorizado() {

		int statusCode = RestAssured.get("http://localhost:" + SkillApiPrepareTest.getPortTest(environment))
				.getStatusCode();
		assertTrue(statusCode == HttpStatus.FORBIDDEN.value());
	}

}
