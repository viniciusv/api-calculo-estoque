package br.com.ubs.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.model.Produto;

@Component
public interface CalculoDistribuicaoService {

	List<LojistaDto> returnLojistasComProdutos(List<Produto> produtos, List<LojistaDto> lojistas);
}
