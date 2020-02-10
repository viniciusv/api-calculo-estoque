package br.com.ubs.api.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import br.com.ubs.api.service.ProdutoService;
import br.com.ubs.api.service.exceptions.ProdutoNotFoundException;
import br.com.ubs.api.service.exceptions.ValidationNotFoundException;

@Service
public class CalculoApiServiceImpl implements CalculoApiService{
	
	@Autowired
	private ProdutoService produtoService; 
	
	private List<Produto> produtos;
	private List<LojistaDto> lojistas;
	
	private BigDecimal somatorioDaQuantidade;
	private BigDecimal somatorioDoVolume;
	//private BigDecimal mediaDePreço;
	
	private static final int ZERO = 0; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculoApiServiceImpl.class);

	@Override
	public List<LojistaDto> returnLojistasComProdutos(String nomeProduto, int quantidadeDeLojistas) {
		
		this.validaInformacoes(nomeProduto, quantidadeDeLojistas);
		this.carregaProdutos(nomeProduto);	
		this.inicializaVariaveis();
		this.inicializaLojistasDto(quantidadeDeLojistas);
		this.distribuiProdutosPorLojistas(BigDecimal.valueOf(quantidadeDeLojistas));	
		
		return this.lojistas;
		
	}

	private void validaInformacoes(String nomeProduto, int quantidadeDeLojistas) {
		
		if(nomeProduto.equals(""))
			throw new ValidationNotFoundException("Produto vazio!");
		
		if(quantidadeDeLojistas == 0)
			throw new ValidationNotFoundException("Quantidade de lojista tem que ser maior que 0!");
		
	}

	private void inicializaLojistasDto(int quantidadeDeLojistas) {
		this.lojistas = new ArrayList<LojistaDto>();
		
		for(int cont=0; cont < quantidadeDeLojistas; cont++) {
			LojistaDto lojistaDto = new LojistaDto("lojista-"+cont);
			lojistas.add(lojistaDto);
		}
		
	}

	private void distribuiProdutosPorLojistas(BigDecimal quantidadeDeLojistas) {
		
		boolean isPulo = false;
		
		for (Produto produto : this.produtos) {
			int controleDeSoma = 0;
			
			BigDecimal novaQuantidade = produto.getQuantity().divide(quantidadeDeLojistas, MathContext.DECIMAL128);
			novaQuantidade = novaQuantidade.setScale(0, RoundingMode.DOWN);
			
			BigDecimal disponivel = novaQuantidade.multiply(quantidadeDeLojistas);
			disponivel = produto.getQuantity().subtract(disponivel);
			
			int pulo = this.lojistas.size() - disponivel.intValue();
		
			for (LojistaDto lojista : this.lojistas) {
				
				BigDecimal quantidadeProdutoPorLojista = new BigDecimal(novaQuantidade.intValue());
				
				if(disponivel.intValue() != ZERO && controleDeSoma < disponivel.intValue()) {
					if(!isPulo || pulo == ZERO) {
						quantidadeProdutoPorLojista = novaQuantidade.add(BigDecimal.ONE);
						controleDeSoma++;
						isPulo = true;
					}else {
						pulo--;
						isPulo = false;
					}
				}					
				
				ProdutoDto produtoDto = new ProdutoDto(produto.getProduct(), quantidadeProdutoPorLojista, produto.getPrice());
				lojista.addProduto(produtoDto);
			}	
		}
		
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
		
		if(produtos == null ||produtos.size() == 0) 
			throw new ProdutoNotFoundException("Produto não encontrado!");
		
	}

}
