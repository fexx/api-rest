package br.com.fernandoevangelista.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandoevangelista.controller.model.Car;
import br.com.fernandoevangelista.exception.CarException;
import br.com.fernandoevangelista.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api(tags = "API de carros", value = "API com operações de carros")
@RequestMapping(value ="/v1/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@ApiOperation(value = "Retorna uma lista de carros")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a lista de carros"),
	    @ApiResponse(code = 500, message = "Caso aconteça uma exeção"),
	})
	@GetMapping
	public List<Car> getCars() {
    	return carService.getCars();
	}
	
	@ApiOperation(value = "Retorna um carro pelo id informado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o carro"),
	    @ApiResponse(code = 500, message = "Caso aconteça uma exeção"),
	})
	@GetMapping("/{id}")
	public Car getCar(@PathVariable(value = "id") Long id) throws CarException {
		return carService.getCarById(id);
	}
	
	@ApiOperation(value = "Atualiza um carro")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o carro atualizado"),
	    @ApiResponse(code = 500, message = "Caso aconteça uma exeção"),
	})
	@PutMapping
	public Car updateCar(@Valid @RequestBody Car car) throws CarException {
		return carService.saveCar(car);
	}
	
	@ApiOperation(value = "Salva um carro")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o carro salvo"),
	    @ApiResponse(code = 500, message = "Caso aconteça uma exeção"),
	})
	@PostMapping
	public Car saveCar(@Valid @RequestBody Car car) throws CarException {
		return carService.saveCar(car);
	}
	
	@ApiOperation(value = "Deleta um carro")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o carro salvo"),
	    @ApiResponse(code = 500, message = "Caso aconteça uma exeção"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") Long id) throws CarException {
		carService.deleteCar(id);
		return new ResponseEntity<>("Carro deletado com sucesso", HttpStatus.OK);
	}
	
	
}
