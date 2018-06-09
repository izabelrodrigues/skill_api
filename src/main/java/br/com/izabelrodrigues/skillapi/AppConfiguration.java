package br.com.izabelrodrigues.skillapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe de configuração da aplicação
 * @author Izabel Rodrigues
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "br.com.izabelrodrigues.skillapi")
public class AppConfiguration extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AppConfiguration.class);

	}

	public static void main(String[] args) {

		SpringApplication.run(AppConfiguration.class, args);
	}

}
