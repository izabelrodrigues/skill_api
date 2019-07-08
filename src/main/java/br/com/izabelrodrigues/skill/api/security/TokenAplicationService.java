package br.com.izabelrodrigues.skill.api.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.izabelrodrigues.skill.api.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe responsável por encapsular as lógicas de geração e manipulação de
 * token
 *
 * @author Izabel Rodrigues
 *
 */
@Service
public class TokenAplicationService {

	private static final String JWT_ISSUER = "SKILL API";

	@Value("${skill.api.jwt.secret}")
	private String secretKey;

	@Value("${skill.api.jwt.expiration}")
	private String expiration;

	/**
	 * Gera token utilizando JWT
	 *
	 * @param authentication
	 * @return
	 */
	public String gerarToken(Authentication authentication) {

		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

		Date hoje = new Date();
		Date expirationDate = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer(JWT_ISSUER).setSubject(usuarioLogado.getId().toString()).setIssuedAt(hoje)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	/**
	 * Verifica se o token informado é válido
	 *
	 * @param token
	 * @return
	 */
	public boolean isTokenValido(String token) {
		try {
			Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return jwsClaims != null;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Obtem o id do usuário através do token informado
	 *
	 * @param token
	 * @return
	 */
	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	/**
	 * @return the expiration
	 */
	public String getExpiration() {
		return expiration;
	}

}
