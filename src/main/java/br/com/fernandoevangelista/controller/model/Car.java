package br.com.fernandoevangelista.controller.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe com propriedades de um carro
 * 
 * @author Fernando Evangelista
 *
 */
@ApiModel(description = "Classe com propriedades de um carro")
@Entity
public class Car {

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "Código do carro", example = "1", required = true, position = 0)
	private Long id;
	
	@NotNull(message = "Modelo do carro não pode ser nulo")
	@NotEmpty(message = "Modelo do carro não pode ser vazio")
	@NotBlank(message = "Modelo do carro não pode ser um espaçamento")
	@ApiModelProperty(value = "Modelo do carro", example = "Mercedes c180", required = true, position = 1)
	private String model;
	
	@NotNull(message = "Cor do carro não pode ser nulo")
	@NotEmpty(message = "cor do carro não pode ser vazio")
	@NotBlank(message = "Cor do carro não pode ser um espaçamento")
	@ApiModelProperty(value = "Cor do carro", example = "Branco", required = true, position = 2)
	private String color;
	
	@NotNull(message = "Preço do carro não pode ser nulo")
	@ApiModelProperty(value = "Preço do carro", example = "100", required = true, position = 3)
	@Min(message = "Preço deve ser no mínimo 1", value = 1)
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
