package br.com.ubs.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ubs.api.model.Produto;

@Component
public interface ProdutoService {
	
	List<Produto> findByProduto(String nomeProduto);
}
