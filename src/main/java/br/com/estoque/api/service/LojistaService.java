package br.com.estoque.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.estoque.api.dto.LojistaDto;

@Component
public interface LojistaService {
	
	List<LojistaDto> criaLojistas(int quantidade);

}
