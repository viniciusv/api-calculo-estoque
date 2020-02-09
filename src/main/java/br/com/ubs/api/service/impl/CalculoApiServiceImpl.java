package br.com.ubs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.model.Produto;
import br.com.ubs.api.service.CalculoApiService;
import br.com.ubs.api.service.ProdutoService;

@Service
public class CalculoApiServiceImpl implements CalculoApiService{
	
	@Autowired
	private ProdutoService produtoService; 

	@Override
	public List<LojistaDto> returnLojistasComProdutos(String nomeProduto, int quantidadeDeLojistas) {
		
		List<Produto> produtos = this.produtoService.findByProduto(nomeProduto);
		
		return null;
	}

}
