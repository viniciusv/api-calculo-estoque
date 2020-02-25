package br.com.estoque.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.estoque.api.dto.LojistaDto;
import br.com.estoque.api.service.LojistaService;
import br.com.estoque.api.service.exceptions.ValidationNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LojistaServiceImpl implements LojistaService{

	@Override
	public List<LojistaDto> criaLojistas(int quantidade) {
		
		this.validaQuantidade(quantidade);
		
		List<LojistaDto> lojistas = new ArrayList<LojistaDto>();
		
		for(int cont=0; cont < quantidade; cont++) {
			LojistaDto lojistaDto = LojistaDto.builder().lojista("Lojista-"+cont).build();
			lojistas.add(lojistaDto);
			log.debug("Criado Lojista-"+cont);
		}
		
		return lojistas;
	}

	private void validaQuantidade(int quantidade) {
		
		if(quantidade == 0) {
			log.error("Quantidade de lojista tem que ser maior que 0!");
			throw new ValidationNotFoundException("Quantidade de lojista tem que ser maior que 0!");
		}
			
		
	}

}
