package br.com.kiaser.forum.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroDeFormularioDto {
	
	private String campo;
	private String erro;
	
	

}
