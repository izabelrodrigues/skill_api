package br.com.izabelrodrigues.skill.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import br.com.izabelrodrigues.skill.api.ResetDatabaseTestExecutionListener;
import br.com.izabelrodrigues.skill.api.SkillApiApplication;
import br.com.izabelrodrigues.skill.api.dto.SkillDto;
import br.com.izabelrodrigues.skill.api.model.Skill;
import br.com.izabelrodrigues.skill.api.util.SkillApiUtil;

@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS, listeners = {
		ResetDatabaseTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkillApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SkillControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username = "user1", password = "pwd", roles = "")
	@Transactional
	public void createSkill() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/skills")
				.content(SkillApiUtil.asJsonString(new SkillDto(new Skill(99l, "teste2", "teste2", true))))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

	}

}
