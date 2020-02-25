package br.com.estoque.api.config;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.estoque.api.model.Produto;
import br.com.estoque.api.service.ProdutoService;

@Configuration
@Profile("test")
public class DatabaseConfig {

	@Autowired
	ProdutoService produtoService;
	
	@Bean
	public void instantiateDatabase() throws ParseException {
		this.instantiateTestDatabase();
	}

	private void instantiateTestDatabase() {
		
		List<Produto> estoques = this.produtoService.findAll();
		
		if(estoques.size() == 0) {
			Produto produto = Produto.builder().product("EMMS").quantity(new BigDecimal(74)).price(new BigDecimal(3.75)).volume(BigDecimal.ZERO).type("XL").industry("Broadcasting").origin("TX").file_import("data_1.json").build();
			Produto produto1 = Produto.builder().product("EMMS").quantity(new BigDecimal(36)).price(new BigDecimal(5.39)).volume(BigDecimal.ZERO).type("3XL").industry("Broadcasting").origin("MN").file_import("data_1.json").build();
			Produto produto2 = Produto.builder().product("EMMS").quantity(new BigDecimal(99)).price(new BigDecimal(5.80)).volume(BigDecimal.ZERO).type("2XL").industry("Broadcasting").origin("MI").file_import("data_1.json").build();
			Produto produto3 = Produto.builder().product("EMMS").quantity(new BigDecimal(61)).price(new BigDecimal(7.45)).volume(BigDecimal.ZERO).type("2XL").industry("Broadcasting").origin("LA").file_import("data_1.json").build();
				
			this.produtoService.save(produto);
			this.produtoService.save(produto1);
			this.produtoService.save(produto2);
			this.produtoService.save(produto3);	
		}		
	}
}
