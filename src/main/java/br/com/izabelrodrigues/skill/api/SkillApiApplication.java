package br.com.izabelrodrigues.skill.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport // Habilita suporte para paginação automática
@EnableCaching //Habilita suporte a cache
public class SkillApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillApiApplication.class, args);
	}

}
