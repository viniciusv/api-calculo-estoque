package br.com.estoque.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.api.model.Produto;
import br.com.estoque.api.repository.ProdutoRepository;
import br.com.estoque.api.service.ProdutoService;
import br.com.estoque.api.service.exceptions.ProdutoNotFoundException;
import br.com.estoque.api.service.exceptions.ValidationNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoServiceImpl.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findByProduto(String nomeProduto) {
		
		this.validaNomeProduto(nomeProduto);	
		
		List<Produto> produtos = this.produtoRepository.findByProduct(nomeProduto); 
		
		this.produtoEncontrado(produtos);
		
		return produtos;
	}

	private void produtoEncontrado(List<Produto> produtos) {
		
		if(produtos == null ||produtos.size() == 0) 
			throw new ProdutoNotFoundException("Produto não encontrado!");	
		
	}

	private void validaNomeProduto(String nomeProduto) {
		
		if(nomeProduto.equals(""))
			throw new ValidationNotFoundException("Nome do Produto vazio!");
		
	} 


}
