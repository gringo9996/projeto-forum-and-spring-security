package br.com.kiaser.forum.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.kiaser.forum.modelo.Usuario;
import br.com.kiaser.forum.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperartoken(request);
		boolean valido = tokenService.isValido(token);

		if (valido) {

			autenticarCliente(token);

		}

		filterChain.doFilter(request, response);

	}

	private String recuperartoken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;

		}
		return token.substring(7, token.length());
	}

	private void autenticarCliente(String token) {

		Long idUsuario = tokenService.getIdUsuario(token);

		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

}
