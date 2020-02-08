package br.com.ubs.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.service.CalculoApiService;

@Service
public class CalculoApiServiceImpl implements CalculoApiService{

	@Override
	public List<LojistaDto> returnProdutosPorQuantidadeDeLojistas(String nomeProduto, int quantidadeDeLojistas) {
		
		return null;
	}

}
