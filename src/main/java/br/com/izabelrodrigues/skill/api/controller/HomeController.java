/**
 * 
 */
package br.com.izabelrodrigues.skill.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Izabel Rodrigues
 *
 */
@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "Bem vindo ao sistema de controle de Skills";
	}

}
