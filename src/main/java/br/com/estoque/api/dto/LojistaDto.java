package br.com.estoque.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LojistaDto implements Serializable{

	private static final long serialVersionUID = -1025486225937803496L;

	@NonNull private String lojista;
	@NonNull private BigDecimal quantidadeTotal;
	@NonNull private BigDecimal financeiro;
	@NonNull private BigDecimal precoMedio;
	
	private List<ProdutoDto> produtos;
	
	public void addProduto(ProdutoDto produtoDto) {
		if(this.produtos == null) {
			this.produtos = new ArrayList<ProdutoDto>();
		}
		this.produtos.add(produtoDto);
		
		this.quantidadeTotal = this.quantidadeTotal.add(produtoDto.getQuantidade());
		this.financeiro = this.financeiro.add(produtoDto.getVolume());
		this.precoMedio = this.financeiro.divide(this.quantidadeTotal, MathContext.DECIMAL128);		
	}
	
	
}
