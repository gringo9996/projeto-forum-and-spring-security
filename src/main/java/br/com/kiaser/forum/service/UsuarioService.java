package br.com.kiaser.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kiaser.forum.modelo.Usuario;
import br.com.kiaser.forum.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	public Optional<Usuario> findByEmail(String email) {
		
		
		return usuarioRepository.findByEmail(email);
	}
	
	

}
