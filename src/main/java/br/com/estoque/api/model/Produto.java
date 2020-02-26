package br.com.estoque.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto implements Serializable{
	
	private static final long serialVersionUID = -7792490747594316061L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String product;
	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal volume;
	private String type;
	private String industry;
	private String origin;
	private String file_import;
	
	
}
