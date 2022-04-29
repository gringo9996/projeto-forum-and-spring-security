package br.com.kiaser.forum.controller;

import javax.validation.Valid;

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

import br.com.kiaser.forum.controller.dto.LoginDto;
import br.com.kiaser.forum.controller.dto.TokenDto;
import br.com.kiaser.forum.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto loginForm) {

		UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();

		try {
			Authentication authentication = authenticationManager.authenticate(dadosLogin);

			// devolver token /geracao token

			String token = tokenService.gerarToken(authentication);

			// System.out.println(token)
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));

		} catch (AuthenticationException e) {

			return ResponseEntity.badRequest().build();

		}

	}

}
