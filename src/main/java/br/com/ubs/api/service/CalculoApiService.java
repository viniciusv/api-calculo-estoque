package br.com.ubs.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ubs.api.dto.LojistaDto;

@Component
public interface CalculoApiService {
	
	List<LojistaDto> returnProdutosPorQuantidadeDeLojistas(String nomeProduto, int quantidadeDeLojistas);
}
