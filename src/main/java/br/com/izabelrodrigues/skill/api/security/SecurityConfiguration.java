package br.com.izabelrodrigues.skill.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.izabelrodrigues.skill.api.repository.UsuarioRepository;

/**
 * Classe responsável pelas configurações de segurança
 *
 * @author Izabel Rodrigues
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private TokenAplicationService tokenApplicationService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configuração de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configuração de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * .and().addFilterBefore(new AutenticacaoViaTokenFilter(),
		 * UsernamePasswordAuthenticationFilter.class): faz com as requisições sejam
		 * interceptadas pela classe AutenticacaoViaTokenFilter
		 */
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/skills").permitAll()
				.antMatchers(HttpMethod.GET, "/skills/*").permitAll().antMatchers(HttpMethod.POST, "/auth").permitAll()
				.anyRequest().authenticated().and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoViaTokenFilter(tokenApplicationService, usuarioRepository),
						UsernamePasswordAuthenticationFilter.class);
		// .and().formLogin();: faz a autenticação via formulário utilizando o form de
		// login. cria sessão

	}

	/**
	 * Configurações de segurança para recursos estáticos(js,css, imagens, etc)
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception
	 */

}
