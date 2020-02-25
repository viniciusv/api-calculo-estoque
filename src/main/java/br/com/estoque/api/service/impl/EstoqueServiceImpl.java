package br.com.estoque.api.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.api.dto.EstoqueDto;
import br.com.estoque.api.dto.LojistaDto;
import br.com.estoque.api.model.Produto;
import br.com.estoque.api.service.CalculoDistribuicaoService;
import br.com.estoque.api.service.EstoqueService;
import br.com.estoque.api.service.LojistaService;
import br.com.estoque.api.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstoqueServiceImpl implements EstoqueService {
	
	@Autowired
	private ProdutoService produtoService; 
	
	@Autowired
	private LojistaService lojistaService; 
	
	@Autowired
	private CalculoDistribuicaoService calculoDistribuicaoService; 
	
	@Override
	public EstoqueDto returnEstoqueAtualizado(String produto, int quantidadeLojistas) {
		List<Produto> produtos = this.carregaProdutos(produto);
		List<LojistaDto> lojistas = this.criaLojistas(quantidadeLojistas);
		lojistas = this.distribuiProdutosPorLojistas(produtos, lojistas);
		return this.criaEstoque(produtos, lojistas);
	}

	private List<Produto> carregaProdutos(String nomeProduto) {
		return this.produtoService.findByProduto(nomeProduto);
	}
	
	private List<LojistaDto> criaLojistas(int quantidadeDeLojistas) {
		return this.lojistaService.criaLojistas(quantidadeDeLojistas);		
	}

	private List<LojistaDto> distribuiProdutosPorLojistas(List<Produto> produtos, List<LojistaDto> lojistas) {
		return this.calculoDistribuicaoService.returnLojistasComProdutos(produtos, lojistas);
	}

	public EstoqueDto criaEstoque(List<Produto> produtos, List<LojistaDto> lojistas) {
		
		IntSummaryStatistics somatorioDaQuantidadeStatistics = produtos.stream().mapToInt(p -> p.getQuantity().intValue()).summaryStatistics();
		DoubleSummaryStatistics somatorioDoVolumeStatistics = produtos.stream().mapToDouble(p -> p.getVolume().doubleValue()).summaryStatistics();
		
		BigDecimal somatorioDaQuantidade = BigDecimal.valueOf(somatorioDaQuantidadeStatistics.getSum());
		BigDecimal somatorioDoVolume =  BigDecimal.valueOf(somatorioDoVolumeStatistics.getSum());
		BigDecimal mediaDePreco = somatorioDoVolume.divide(somatorioDaQuantidade, MathContext.DECIMAL128);
		
		log.info("Criado Estoque com sucesso!");
		
		return new EstoqueDto(somatorioDaQuantidade, somatorioDoVolume, mediaDePreco, lojistas);
	}


	



}
