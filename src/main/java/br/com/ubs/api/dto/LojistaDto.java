package br.com.ubs.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LojistaDto implements Serializable{

	private static final long serialVersionUID = -1025486225937803496L;

	private String lojista;
	private Integer quantidadeTotal;
	private BigDecimal financeiro;
	private BigDecimal precoMedio;
	
	public LojistaDto(String lojista, Integer quantidadeTotal, BigDecimal financeiro, BigDecimal precoMedio) {
		this.lojista = lojista;
		this.quantidadeTotal = quantidadeTotal;
		this.financeiro = financeiro;
		this.precoMedio = precoMedio;
	}

	public String getLojista() {
		return lojista;
	}

	public void setLojista(String lojista) {
		this.lojista = lojista;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public BigDecimal getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(BigDecimal financeiro) {
		this.financeiro = financeiro;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((financeiro == null) ? 0 : financeiro.hashCode());
		result = prime * result + ((lojista == null) ? 0 : lojista.hashCode());
		result = prime * result + ((precoMedio == null) ? 0 : precoMedio.hashCode());
		result = prime * result + ((quantidadeTotal == null) ? 0 : quantidadeTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LojistaDto other = (LojistaDto) obj;
		if (financeiro == null) {
			if (other.financeiro != null)
				return false;
		} else if (!financeiro.equals(other.financeiro))
			return false;
		if (lojista == null) {
			if (other.lojista != null)
				return false;
		} else if (!lojista.equals(other.lojista))
			return false;
		if (precoMedio == null) {
			if (other.precoMedio != null)
				return false;
		} else if (!precoMedio.equals(other.precoMedio))
			return false;
		if (quantidadeTotal == null) {
			if (other.quantidadeTotal != null)
				return false;
		} else if (!quantidadeTotal.equals(other.quantidadeTotal))
			return false;
		return true;
	}	
}
