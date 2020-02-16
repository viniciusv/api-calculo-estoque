package br.com.ubs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ubs.api.dto.EstoqueDto;
import br.com.ubs.api.service.EstoqueService;

@RestController
@RequestMapping(value="/estoque/calculo")
public class CalculoDistruicaoController {
	
	@Autowired
	private EstoqueService estoqueService; 
	
	@GetMapping
	public ResponseEntity<EstoqueDto>  calculaQuantidadeDeProdutosPorLojistaRequestParam(
			@RequestParam(value="produto", required=true) String produto,
			@RequestParam(value="lojistas", required=true) int lojistas) {
				
		EstoqueDto estoqueDto = this.estoqueService.returnEstoqueAtualizado(produto, lojistas); 
		
		return ResponseEntity.ok().body(estoqueDto);
	}
	
	@GetMapping(value = "{produto}/{lojistas}")
	public ResponseEntity<EstoqueDto> calculaQuantidadeDeProdutosPorLojistaPathVariable(
			@PathVariable String produto, 
			@PathVariable int lojistas) {
				
		EstoqueDto estoqueDto = this.estoqueService.returnEstoqueAtualizado(produto, lojistas); 
		
		return ResponseEntity.ok().body(estoqueDto);
	}

}
