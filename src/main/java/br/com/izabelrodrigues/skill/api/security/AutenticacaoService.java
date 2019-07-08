package br.com.izabelrodrigues.skill.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.izabelrodrigues.skill.api.model.Usuario;
import br.com.izabelrodrigues.skill.api.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		throw new UsernameNotFoundException("Dados inválidos");
	}

}
