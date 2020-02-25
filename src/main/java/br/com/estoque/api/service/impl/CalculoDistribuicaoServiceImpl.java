package br.com.estoque.api.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.estoque.api.dto.LojistaDto;
import br.com.estoque.api.dto.ProdutoDto;
import br.com.estoque.api.model.Produto;
import br.com.estoque.api.service.CalculoDistribuicaoService;

@Service
public class CalculoDistribuicaoServiceImpl implements CalculoDistribuicaoService{

	private List<Produto> produtos;
	private List<LojistaDto> lojistas;
	
	private static final int ZERO = 0; 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculoDistribuicaoServiceImpl.class);

	@Override
	public List<LojistaDto> returnLojistasComProdutos(List<Produto> produtos, List<LojistaDto> lojistas) {
		this.produtos = produtos;
		this.lojistas = lojistas;

		this.iniciaCalculoDeDistribuicao();	
		
		return this.lojistas;
	}

	private void iniciaCalculoDeDistribuicao() {
		
		Boolean isPulo = new Boolean(false);
		BigDecimal quantidadeDeLojistas = BigDecimal.valueOf(this.lojistas.size());
		
		for (Produto produto : this.produtos) {
			
			BigDecimal novaQuantidadeDeProdutosPorLojistas = this.calculoQuantidadeDeProduto(produto.getQuantity(), quantidadeDeLojistas); 
			BigDecimal quantidadeDeProdutosDisponiveisSobrando = this.calculoDeProdutosDisponiveis(novaQuantidadeDeProdutosPorLojistas, quantidadeDeLojistas, produto.getQuantity()) ;
			
			isPulo = this.distribuiProdutosPorLojistas(novaQuantidadeDeProdutosPorLojistas, quantidadeDeProdutosDisponiveisSobrando, isPulo, produto);
		}
		
	}

	private Boolean distribuiProdutosPorLojistas(BigDecimal quantidadeDeProdutos, BigDecimal quantidadeDeProdutosDisponiveisSobrando, Boolean isPulo, Produto produto) {
		
		int controleDaQuantidadeDeProdutosDisponiveis = 0;
		int quantidadeDeSaltos = this.calculoDeSalto(quantidadeDeProdutosDisponiveisSobrando);
		
		for (LojistaDto lojista : this.lojistas) {
			
			BigDecimal quantidadeProdutoPorLojistaAux = new BigDecimal(quantidadeDeProdutos.intValue());
			
			if(this.existeProdutosDisponiveis(quantidadeDeProdutosDisponiveisSobrando, controleDaQuantidadeDeProdutosDisponiveis)) {
				
				if(this.possoSaltarLojista(isPulo, quantidadeDeSaltos)) {
					quantidadeProdutoPorLojistaAux = quantidadeDeProdutos.add(BigDecimal.ONE);
					controleDaQuantidadeDeProdutosDisponiveis++;
					isPulo = Boolean.TRUE;
				}else {
					quantidadeDeSaltos--;
					isPulo = Boolean.FALSE;
				}  
				
			}					
			
			ProdutoDto produtoDto = ProdutoDto.builder().produto(produto.getProduct()).quantidade(quantidadeProdutoPorLojistaAux).preco(produto.getPrice()).build(); 
			lojista.addProduto(produtoDto);
		}	
		
		return isPulo;
		
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
}
