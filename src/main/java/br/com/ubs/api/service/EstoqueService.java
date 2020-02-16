package br.com.ubs.api.service;

import org.springframework.stereotype.Component;

import br.com.ubs.api.dto.EstoqueDto;

@Component
public interface EstoqueService {

	EstoqueDto returnEstoqueAtualizado(String produto, int lojistas);

}
