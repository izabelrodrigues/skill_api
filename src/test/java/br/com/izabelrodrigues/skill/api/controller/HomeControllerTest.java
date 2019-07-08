package br.com.izabelrodrigues.skill.api.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.izabelrodrigues.skill.api.SkillApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

	@InjectMocks
	HomeController homeController;

	@Test
	public void quandoAcionadoOMetodoIndexDeveRetornarMensagemBoasVindas() {
		assertEquals("Deveria ter mensagem boas vindas", "Bem vindo ao sistema de controle de Skills",
				homeController.index());
	}

}