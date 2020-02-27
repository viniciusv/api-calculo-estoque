package br.com.estoque.api.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.api.dto.EstoqueDto;
import br.com.estoque.api.service.EstoqueService;

@RestController
@RequestMapping(value="/estoque/calculo")
public class CalculoDistribuicaoController {
	
	@Autowired
	private EstoqueService estoqueService; 
	
	@GetMapping
	@Cacheable("estoque")
	public ResponseEntity<EntityModel<EstoqueDto>> calculaQuantidadeDeProdutosPorLojistaRequestParam(
			@RequestParam(value="produto", required=true) String produto,
			@RequestParam(value="lojistas", required=true) int lojistas) {
				
		EstoqueDto estoqueDto = this.estoqueService.returnEstoqueAtualizado(produto, lojistas); 
		
		return ResponseEntity.ok(new EntityModel<>(estoqueDto,
			    linkTo(methodOn(CalculoDistribuicaoController.class).calculaQuantidadeDeProdutosPorLojistaRequestParam(produto, lojistas)).withSelfRel()));
	}
	
	@GetMapping(value = "{produto}/{lojistas}")
	@Cacheable("estoque")
	public ResponseEntity<EstoqueDto> calculaQuantidadeDeProdutosPorLojistaPathVariable(
			@PathVariable String produto, 
			@PathVariable int lojistas) {
				
		EstoqueDto estoqueDto = this.estoqueService.returnEstoqueAtualizado(produto, lojistas); 
		
		return ResponseEntity.ok().body(estoqueDto);
	}

}
