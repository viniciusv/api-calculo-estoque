package br.com.ubs.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.service.LojistaService;
import br.com.ubs.api.service.exceptions.ValidationNotFoundException;

@Service
public class LojistaServiceImpl implements LojistaService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LojistaServiceImpl.class);

	@Override
	public List<LojistaDto> criaLojistas(int quantidade) {
		
		this.validaQuantidade(quantidade);
		
		List<LojistaDto> lojistas = new ArrayList<LojistaDto>();
		
		for(int cont=0; cont < quantidade; cont++) {
			LojistaDto lojistaDto = new LojistaDto("lojista-"+cont);
			lojistas.add(lojistaDto);
		}
		
		return lojistas;
	}

	private void validaQuantidade(int quantidade) {
		
		if(quantidade == 0)
			throw new ValidationNotFoundException("Quantidade de lojista tem que ser maior que 0!");
		
	}

}
