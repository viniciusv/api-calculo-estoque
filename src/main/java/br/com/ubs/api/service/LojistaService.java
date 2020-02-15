package br.com.ubs.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ubs.api.dto.LojistaDto;

@Component
public interface LojistaService {
	
	List<LojistaDto> criaLojistas(int quantidade);

}
