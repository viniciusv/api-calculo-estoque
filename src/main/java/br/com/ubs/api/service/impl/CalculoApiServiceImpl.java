package br.com.ubs.api.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.dto.ProdutoDto;
import br.com.ubs.api.model.Produto;
import br.com.ubs.api.service.CalculoApiService;
import br.com.ubs.api.service.LojistaService;
import br.com.ubs.api.service.ProdutoService;

@Service
public class CalculoApiServiceImpl implements CalculoApiService{
	
	@Autowired
	private ProdutoService produtoService; 
	
	@Autowired
	private LojistaService lojistaService; 
	
	private List<Produto> produtos;
	private List<LojistaDto> lojistas;
	
	private BigDecimal somatorioDaQuantidade;
	private BigDecimal somatorioDoVolume;
	//private BigDecimal mediaDePreço;
	
	private static final int ZERO = 0; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculoApiServiceImpl.class);

	@Override
	public List<LojistaDto> returnLojistasComProdutos(String nomeProduto, int quantidadeDeLojistas) {
		
		this.criaLojistas(quantidadeDeLojistas);
		this.carregaProdutos(nomeProduto);	
		
		this.inicializaVariaveis();
		
		this.iniciaCalculoDeDistribuicao(BigDecimal.valueOf(quantidadeDeLojistas));	
		
		return this.lojistas;
		
	}

	private void iniciaCalculoDeDistribuicao(BigDecimal quantidadeDeLojistas) {
		
		Boolean isPulo = false;
		
		for (Produto produto : this.produtos) {
			
			BigDecimal novaQuantidadeDeProdutosPorLojistas = this.calculoQuantidadeDeProduto(produto.getQuantity(), quantidadeDeLojistas); 
			BigDecimal quantidadeDeProdutosDisponiveisSobrando = this.calculoDeProdutosDisponiveis(novaQuantidadeDeProdutosPorLojistas, quantidadeDeLojistas, produto.getQuantity()) ;
			
			this.distribuiProdutosPorLojistas(novaQuantidadeDeProdutosPorLojistas, quantidadeDeProdutosDisponiveisSobrando, isPulo, produto);
		}
		
	}

	private void distribuiProdutosPorLojistas(BigDecimal quantidadeDeProdutos, BigDecimal quantidadeDeProdutosDisponiveisSobrando, Boolean isPulo, Produto produto) {
		
		int controleDaQuantidadeDeProdutosDisponiveis = 0;
		int quantidadeDeSaltos = this.calculoDeSalto(quantidadeDeProdutosDisponiveisSobrando);
		
		for (LojistaDto lojista : this.lojistas) {
			
			BigDecimal quantidadeProdutoPorLojistaAux = new BigDecimal(quantidadeDeProdutos.intValue());
			
			if(this.existeProdutosDisponiveis(quantidadeDeProdutosDisponiveisSobrando, controleDaQuantidadeDeProdutosDisponiveis)) {
				
				if(this.possoSaltarLojista(isPulo, quantidadeDeSaltos)) {
					quantidadeProdutoPorLojistaAux = quantidadeDeProdutos.add(BigDecimal.ONE);
					controleDaQuantidadeDeProdutosDisponiveis++;
					isPulo = true;
				}else {
					quantidadeDeSaltos--;
					isPulo = false;
				}
				
			}					
			
			ProdutoDto produtoDto = new ProdutoDto(produto.getProduct(), quantidadeProdutoPorLojistaAux, produto.getPrice());
			lojista.addProduto(produtoDto);
		}	
		
	}

	private boolean possoSaltarLojista(Boolean isPulo, int quantidadeDeSaltos) {
		return !isPulo || quantidadeDeSaltos == ZERO;
	}

	private boolean existeProdutosDisponiveis(BigDecimal quantidadeDeProdutosDisponiveisSobrando, int controleDaQuantidadeDeProdutosDisponiveis) {
		return quantidadeDeProdutosDisponiveisSobrando.intValue() != ZERO && controleDaQuantidadeDeProdutosDisponiveis < quantidadeDeProdutosDisponiveisSobrando.intValue();
		
	}

	private int calculoDeSalto(BigDecimal disponivel) {
		return this.lojistas.size() - disponivel.intValue();
	}

	private BigDecimal calculoQuantidadeDeProduto(BigDecimal quantidadeDeProdutos, BigDecimal quantidadeDeLojistas) {
		BigDecimal novaQuantidade = quantidadeDeProdutos.divide(quantidadeDeLojistas, MathContext.DECIMAL128);
		novaQuantidade = novaQuantidade.setScale(0, RoundingMode.DOWN);
		return novaQuantidade;
	}

	private BigDecimal calculoDeProdutosDisponiveis(BigDecimal novaQuantidade, BigDecimal quantidadeDeLojistas, BigDecimal quantidadeDeProdutos) {
		BigDecimal disponivel = novaQuantidade.multiply(quantidadeDeLojistas);
		disponivel = quantidadeDeProdutos.subtract(disponivel);
		return disponivel;
	}

	private void inicializaVariaveis() {
		
		IntSummaryStatistics somatorioDaQuantidade = this.produtos.stream().mapToInt(p -> p.getQuantity().intValue()).summaryStatistics();
		DoubleSummaryStatistics somatorioDoVolume = this.produtos.stream().mapToDouble(p -> p.getVolume().doubleValue()).summaryStatistics();
		
		this.somatorioDaQuantidade = BigDecimal.valueOf(somatorioDaQuantidade.getSum());
		this.somatorioDoVolume =  BigDecimal.valueOf(somatorioDoVolume.getSum());
		
		//this.mediaDePreço = this.somatorioDoVolume.divide(this.somatorioDaQuantidade, MathContext.DECIMAL128);
		
	}

	private void carregaProdutos(String nomeProduto) {
		this.produtos = this.produtoService.findByProduto(nomeProduto);
	}
	
	private void criaLojistas(int quantidadeDeLojistas) {
		this.lojistas = this.lojistaService.criaLojistas(quantidadeDeLojistas);		
	}

}
