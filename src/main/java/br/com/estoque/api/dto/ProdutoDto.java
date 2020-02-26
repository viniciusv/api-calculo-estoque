package br.com.estoque.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProdutoDto implements Serializable{

	private static final long serialVersionUID = 8390634332867069126L;

	private String produto;
	private BigDecimal quantidade;
	private BigDecimal preco;
	private BigDecimal volume;
	
}
