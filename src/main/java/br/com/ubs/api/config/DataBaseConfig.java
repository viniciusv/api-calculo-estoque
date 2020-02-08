package br.com.ubs.api.config;

import java.math.BigDecimal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ubs.api.model.Estoque;
import br.com.ubs.api.repository.CalculoApiRepository;

@Configuration
@Profile("test")
public class DataBaseConfig {

	@Autowired
	CalculoApiRepository calculoApiRepository;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		this.instantiateTestDatabase();
		return true;
	}

	private void instantiateTestDatabase() {
		Estoque estoque = new Estoque("EMMS", new Integer(74), new BigDecimal(3.75),"XL","Broadcasting", "TX", "data_1.json");
		this.calculoApiRepository.save(estoque);				
	}
}
