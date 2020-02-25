package br.com.estoque.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.estoque.api.model.Produto;

@Component
public interface ProdutoService {
	
	List<Produto> findByProduto(String nomeProduto);
}
