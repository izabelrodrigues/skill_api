package br.com.izabelrodrigues.skill.api.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.izabelrodrigues.skill.api.Constants;
import br.com.izabelrodrigues.skill.api.model.Usuario;
import br.com.izabelrodrigues.skill.api.repository.UsuarioRepository;

/**
 * Classe responsável por interceptar todas as requisições
 *
 * @author Izabel Rodrigues
 *
 */
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenAplicationService tokenApplicationService;

	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenAplicationService tokenApplicationService,
			UsuarioRepository usuarioRepository) {
		this.tokenApplicationService = tokenApplicationService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		boolean isTokenValido = tokenApplicationService.isTokenValido(token);

		if (isTokenValido) {
			autenticarCliente(token);
		}
		// Após validado o token continua a requisição
		filterChain.doFilter(request, response);

	}

	/**
	 * Autentica um usuario através do seu token válido
	 *
	 * @param token
	 */
	private void autenticarCliente(String token) {

		Long id = tokenApplicationService.getIdUsuario(token);
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		Usuario usuario = null;
		if (usuarioOptional.isPresent()) {
			usuario = usuarioOptional.get();
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				null != usuario ? usuario.getAuthorities() : null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	/**
	 * Recupera o token da requisição
	 *
	 * @param request
	 * @return
	 */
	private String recuperarToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		if (null == token || token.isEmpty() || !token.startsWith(Constants.TIPO_AUTENTICACAO + " ")) {
			return null;
		}

		return token.substring(Constants.TIPO_AUTENTICACAO.length() + 1, token.length());
	}

}
