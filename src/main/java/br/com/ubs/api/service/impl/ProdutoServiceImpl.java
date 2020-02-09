package br.com.ubs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubs.api.model.Produto;
import br.com.ubs.api.repository.ProdutoRepository;
import br.com.ubs.api.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findByProduto(String nomeProduto) {
		return this.produtoRepository.findByProduct(nomeProduto);
	} 


}
