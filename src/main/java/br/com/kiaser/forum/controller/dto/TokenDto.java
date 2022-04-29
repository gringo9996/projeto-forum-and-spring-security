package br.com.kiaser.forum.controller.dto;

import lombok.Data;

@Data
public class TokenDto {
	
	private String token;
	
	private String tipo;

	public TokenDto(String token, String tipo) {
		
		this.token=token;
		this.tipo=tipo;

	}

}
