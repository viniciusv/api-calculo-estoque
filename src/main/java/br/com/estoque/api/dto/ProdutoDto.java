package br.com.estoque.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProdutoDto implements Serializable{

	private static final long serialVersionUID = 8390634332867069126L;

	private String produto;
	private BigDecimal quantidade;
	private BigDecimal preco;
	private BigDecimal volume;
	
}
