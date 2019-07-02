package br.com.izabelrodrigues.skill.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SkillApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillApiApplication.class, args);
	}

}
