package br.com.estoque.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.estoque.api.dto.LojistaDto;
import br.com.estoque.api.model.Produto;

@Component
public interface CalculoDistribuicaoService {

	List<LojistaDto> returnLojistasComProdutos(List<Produto> produtos, List<LojistaDto> lojistas);
}
