package br.com.estoque.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class EstoqueDto implements Serializable{

	private static final long serialVersionUID = -1025486225937803496L;
	
	private BigDecimal somatorioDaQuantidade;
	private BigDecimal somatorioDoVolume;
	private BigDecimal mediaDePreco;
	
	private List<LojistaDto> lojistas;

	public EstoqueDto() {
		super();
	}
	
	public EstoqueDto(BigDecimal somatorioDaQuantidade, BigDecimal somatorioDoVolume, BigDecimal mediaDePreco) {
		super();
		this.somatorioDaQuantidade = somatorioDaQuantidade;
		this.somatorioDoVolume = somatorioDoVolume;
		this.mediaDePreco = mediaDePreco;
		this.lojistas = null;
	}

	public EstoqueDto(BigDecimal somatorioDaQuantidade, BigDecimal somatorioDoVolume, BigDecimal mediaDePreco,
			List<LojistaDto> lojistas) {
		super();
		this.somatorioDaQuantidade = somatorioDaQuantidade;
		this.somatorioDoVolume = somatorioDoVolume;
		this.mediaDePreco = mediaDePreco;
		this.lojistas = lojistas;
	}

	public BigDecimal getSomatorioDaQuantidade() {
		return somatorioDaQuantidade;
	}

	public void setSomatorioDaQuantidade(BigDecimal somatorioDaQuantidade) {
		this.somatorioDaQuantidade = somatorioDaQuantidade;
	}

	public BigDecimal getSomatorioDoVolume() {
		return somatorioDoVolume;
	}

	public void setSomatorioDoVolume(BigDecimal somatorioDoVolume) {
		this.somatorioDoVolume = somatorioDoVolume;
	}

	public BigDecimal getMediaDePreco() {
		return mediaDePreco;
	}

	public void setMediaDePreco(BigDecimal mediaDePreco) {
		this.mediaDePreco = mediaDePreco;
	}

	public List<LojistaDto> getLojistas() {
		return lojistas;
	}

	public void setLojistas(List<LojistaDto> lojistas) {
		this.lojistas = lojistas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lojistas == null) ? 0 : lojistas.hashCode());
		result = prime * result + ((mediaDePreco == null) ? 0 : mediaDePreco.hashCode());
		result = prime * result + ((somatorioDaQuantidade == null) ? 0 : somatorioDaQuantidade.hashCode());
		result = prime * result + ((somatorioDoVolume == null) ? 0 : somatorioDoVolume.hashCode());
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
		EstoqueDto other = (EstoqueDto) obj;
		if (lojistas == null) {
			if (other.lojistas != null)
				return false;
		} else if (!lojistas.equals(other.lojistas))
			return false;
		if (mediaDePreco == null) {
			if (other.mediaDePreco != null)
				return false;
		} else if (!mediaDePreco.equals(other.mediaDePreco))
			return false;
		if (somatorioDaQuantidade == null) {
			if (other.somatorioDaQuantidade != null)
				return false;
		} else if (!somatorioDaQuantidade.equals(other.somatorioDaQuantidade))
			return false;
		if (somatorioDoVolume == null) {
			if (other.somatorioDoVolume != null)
				return false;
		} else if (!somatorioDoVolume.equals(other.somatorioDoVolume))
			return false;
		return true;
	}
	
}
