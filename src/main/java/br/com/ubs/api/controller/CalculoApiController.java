package br.com.ubs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ubs.api.dto.LojistaDto;
import br.com.ubs.api.service.CalculoApiService;

@RestController
@RequestMapping(value="/estoque/calculo")
public class CalculoApiController {
	
	@Autowired
	CalculoApiService calculoApiService;
	
	@GetMapping
	public ResponseEntity<List<LojistaDto>>  calculaQuantidadeDeProdutosPorLojistaRequestParam(
			@RequestParam(value="produto", required=true) String produto,
			@RequestParam(value="lojistas", required=true) int lojistas) {
				
		List<LojistaDto> lojistasResultado = null; 
		
		return ResponseEntity.ok().body(lojistasResultado);
	}
	
	@GetMapping(value = "{produto}/{lojistas}")
	public ResponseEntity<List<LojistaDto>> calculaQuantidadeDeProdutosPorLojistaPathVariable(
			@PathVariable String produto, 
			@PathVariable int lojistas) {
				
		List<LojistaDto> lojistasResultado = null; 
		
		return ResponseEntity.ok().body(lojistasResultado);
	}

}
