package br.com.kiaser.forum.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import br.com.kiaser.forum.modelo.Usuario;
import br.com.kiaser.forum.service.UsuarioService;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioService.findByEmail(username);
		if (usuario.isPresent()) {

			// se usuario estiver presente /recupera o usuario
			return usuario.get();

		}

		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}
