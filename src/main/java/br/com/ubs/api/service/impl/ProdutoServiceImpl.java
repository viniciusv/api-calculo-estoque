package br.com.ubs.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubs.api.model.Produto;
import br.com.ubs.api.repository.ProdutoRepository;
import br.com.ubs.api.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoServiceImpl.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findByProduto(String nomeProduto) {
		return this.produtoRepository.findByProduct(nomeProduto);
	} 


}
