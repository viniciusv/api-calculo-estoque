package br.com.estoque.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.api.model.Produto;
import br.com.estoque.api.repository.ProdutoRepository;
import br.com.estoque.api.service.ProdutoService;
import br.com.estoque.api.service.exceptions.ProdutoNotFoundException;
import br.com.estoque.api.service.exceptions.ValidationNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProdutoServiceImpl implements ProdutoService{
	
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
		
		if(produtos == null ||produtos.size() == 0) {
			log.error("Produto não encontrado!");
			throw new ProdutoNotFoundException("Produto não encontrado!");
		}
				
		
	}

	private void validaNomeProduto(String nomeProduto) {
		
		if(nomeProduto.equals("")) {
			log.error("Nome do Produto vazio!");
			throw new ValidationNotFoundException("Nome do Produto vazio!");
		}
			
		
	}

	@Override
	public List<Produto> findAll() {
		log.info("Get Produtos!");
		return this.produtoRepository.findAll();
	}

	@Override
	public void save(Produto produto) {
		this.produtoRepository.save(produto);
		log.info("Produto Criado!");
	} 


}
