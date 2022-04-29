package br.com.kiaser.forum.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.kiaser.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		Usuario logado = (Usuario) authentication.getPrincipal();

		Date data = new Date();

		Date dataExpiration = new Date(data.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("Api kaiser") // quem gerou token
				.setSubject(logado.getId().toString()) // usuario token
				.setIssuedAt(data) // data geração token
				.setExpiration(dataExpiration) // data de expiração
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValido(String token) {

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

			return true;
		} catch (Exception e) {

		}

		return false;
	}

	public Long getIdUsuario(String token) {

		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}

}
