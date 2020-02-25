package br.com.estoque.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
public class LojistaDto implements Serializable{

	private static final long serialVersionUID = -1025486225937803496L;

	private String lojista;
	private BigDecimal quantidadeTotal;
	private BigDecimal financeiro;
	private BigDecimal precoMedio;
	private List<ProdutoDto> produtos;
	
	
}
