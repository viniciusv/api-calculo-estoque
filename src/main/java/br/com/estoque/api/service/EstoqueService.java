package br.com.estoque.api.service;

import org.springframework.stereotype.Component;

import br.com.estoque.api.dto.EstoqueDto;

@Component
public interface EstoqueService {

	EstoqueDto returnEstoqueAtualizado(String produto, int lojistas);

}
