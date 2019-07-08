package br.com.izabelrodrigues.skill.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.izabelrodrigues.skill.api.Constants;
import br.com.izabelrodrigues.skill.api.dto.LoginDto;
import br.com.izabelrodrigues.skill.api.dto.TokenDto;
import br.com.izabelrodrigues.skill.api.security.TokenAplicationService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutenticacaoController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenAplicationService tokenApplicationService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto login) {
		UsernamePasswordAuthenticationToken dadosLogin = login.convertToAuthentication(login);
		try {
			LOGGER.info("Autenticando....");

			Authentication authentication = authenticationManager.authenticate(dadosLogin);

			LOGGER.info("Gerando o token....");
			String token = tokenApplicationService.gerarToken(authentication);

			/**
			 * Responde para a requisição enviando o token com o tipo de autenticação Bearer
			 */
			return ResponseEntity.ok(new TokenDto(token, Constants.TIPO_AUTENTICACAO));
		} catch (AuthenticationException e) {
			LOGGER.error("Falha ao autenticar");
			return ResponseEntity.badRequest().build();
		}

	}

}
