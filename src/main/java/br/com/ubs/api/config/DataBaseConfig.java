package br.com.ubs.api.config;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ubs.api.model.Produto;
import br.com.ubs.api.repository.ProdutoRepository;

@Configuration
@Profile("test")
public class DatabaseConfig {

	@Autowired
	ProdutoRepository estoqueApiRepository;
	
	@Bean
	public void instantiateDatabase() throws ParseException {
		this.instantiateTestDatabase();
	}

	private void instantiateTestDatabase() {
		
		List<Produto> estoques = this.estoqueApiRepository.findAll();
		
		if(estoques.size() == 0) {
			Produto estoque = new Produto("EMMS", new BigDecimal(74), new BigDecimal(3.75),"XL","Broadcasting", "TX", "data_1.json");
			Produto estoque1 = new Produto("EMMS", new BigDecimal(36), new BigDecimal(5.29),"3XL","Broadcasting", "MN", "data_1.json");
			Produto estoque2 = new Produto("EMMS", new BigDecimal(99), new BigDecimal(5.80),"2XL","Broadcasting", "MI", "data_1.json");
			Produto estoque3 = new Produto("EMMS", new BigDecimal(61), new BigDecimal(7.45),"2XL","Broadcasting", "LA", "data_1.json");
			
			this.estoqueApiRepository.save(estoque);
			this.estoqueApiRepository.save(estoque1);
			this.estoqueApiRepository.save(estoque2);
			this.estoqueApiRepository.save(estoque3);	
		}		
	}
}
