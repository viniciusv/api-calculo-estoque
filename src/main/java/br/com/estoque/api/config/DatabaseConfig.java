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
			Produto produto = new Produto("EMMS", new BigDecimal(74), new BigDecimal(3.75),"XL","Broadcasting", "TX", "data_1.json");
			Produto produto1 = new Produto("EMMS", new BigDecimal(36), new BigDecimal(5.39),"3XL","Broadcasting", "MN", "data_1.json");
			Produto produto2 = new Produto("EMMS", new BigDecimal(99), new BigDecimal(5.80),"2XL","Broadcasting", "MI", "data_1.json");
			Produto produto3 = new Produto("EMMS", new BigDecimal(61), new BigDecimal(7.45),"2XL","Broadcasting", "LA", "data_1.json");
			
			this.produtoService.save(produto);
			this.produtoService.save(produto1);
			this.produtoService.save(produto2);
			this.produtoService.save(produto3);	
		}		
	}
}
