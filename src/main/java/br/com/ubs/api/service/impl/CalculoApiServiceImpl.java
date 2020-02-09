package br.com.ubs.api.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.dto.ProdutoDto;
import br.com.ubs.api.model.Produto;
import br.com.ubs.api.service.CalculoApiService;
import br.com.ubs.api.service.ProdutoService;

@Service
public class CalculoApiServiceImpl implements CalculoApiService{
	
	@Autowired
	private ProdutoService produtoService; 
	
	private List<Produto> produtos;
	private List<LojistaDto> lojistas;
	
	private BigDecimal somatorioDaQuantidade;
	private BigDecimal somatorioDoVolume;
	private BigDecimal mediaDePreço;

	@Override
	public List<LojistaDto> returnLojistasComProdutos(String nomeProduto, int quantidadeDeLojistas) {
		
		this.validaInformacoes(nomeProduto, quantidadeDeLojistas);
		this.carregaProdutos(nomeProduto);	
		this.inicializaVariaveis();
		this.inicializaLojistasDto(quantidadeDeLojistas);
		this.inicializaCalculo(quantidadeDeLojistas);	
		
		return this.lojistas;
		
	}

	private void validaInformacoes(String nomeProduto, int quantidadeDeLojistas) {
		
		if(nomeProduto.equals(""))
			throw new RuntimeException("Produto vazio");
		
		if(quantidadeDeLojistas == 0)
			throw new RuntimeException("Quantidade de lojista tem que ser maior que 0!");
		
	}

	private void inicializaLojistasDto(int quantidadeDeLojistas) {
		this.lojistas = new ArrayList<LojistaDto>();
		
		for(int cont=0; cont < quantidadeDeLojistas; cont++) {
			LojistaDto lojistaDto = new LojistaDto("lojista-"+cont);
			lojistas.add(lojistaDto);
		}
		
	}

	private void inicializaCalculo(int quantidadeDeLojistas) {
		
		this.lojistas.forEach(lojista -> {
			this.produtos.forEach(produto->{
				BigDecimal novaQuantidade = produto.getQuantity().divide(BigDecimal.valueOf(quantidadeDeLojistas));
				ProdutoDto produtoDto = new ProdutoDto(produto.getProduct(), novaQuantidade, produto.getPrice());
				lojista.addProduto(produtoDto);
			});
		});
		
	}

	private void inicializaVariaveis() {
		
		IntSummaryStatistics somatorioDaQuantidade = this.produtos.stream().mapToInt(p -> p.getQuantity().intValue()).summaryStatistics();
		DoubleSummaryStatistics somatorioDoVolume = this.produtos.stream().mapToDouble(p -> p.getVolume().doubleValue()).summaryStatistics();
		
		this.somatorioDaQuantidade = BigDecimal.valueOf(somatorioDaQuantidade.getSum());
		this.somatorioDoVolume =  BigDecimal.valueOf(somatorioDoVolume.getSum());
		
		this.mediaDePreço = this.somatorioDoVolume.divide(this.somatorioDaQuantidade, MathContext.DECIMAL128);
		
	}

	private void carregaProdutos(String nomeProduto) {
		
		this.produtos = this.produtoService.findByProduto(nomeProduto);
		
		if(produtos == null ||produtos.size() == 0) 
			throw new RuntimeException("Produto não encontrado!");
		
	}

}
