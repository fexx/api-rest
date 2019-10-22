package br.com.fernandoevangelista.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fernandoevangelista.controller.model.Car;
import br.com.fernandoevangelista.exception.CarException;
import br.com.fernandoevangelista.service.CarService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CarControllerTest {
	
	@InjectMocks
	private CarController carController;
	
	@Mock
	private CarService carService;
	
	@Mock
	private ResponseEntity<Object> responseEntity;
	
	@Test
	public void getCars_notNull_test() {	
		when(carService.getCars()).thenReturn(new ArrayList<>());
		List<Car> listCars = carController.getCars();
		assertNotNull(listCars);
	}
	
	@Test
	public void getCars_null_test() {	
		when(carService.getCars()).thenReturn(null);
		List<Car> listCars = carController.getCars();
		assertNull("Cars null", listCars);
	}
	
	@Test
	public void getCars_empty_test() {
		when(carService.getCars()).thenReturn(new ArrayList<>());
		List<Car> listCars = carController.getCars();
		assertTrue(listCars.isEmpty());
	}
	
	@Test
	public void getCars_notEmpty_test() {
		List<Car> cars = new ArrayList<>();
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		cars.add(car);
		when(carService.getCars()).thenReturn(cars);
		List<Car> listCars = carController.getCars();
		assertFalse(listCars.isEmpty());
	}
	
	@Test
	public void getCar_equals_test() throws CarException {	
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carService.getCarById(1l)).thenReturn(car);
		Car carReturn = carController.getCar(1l);
		assertEquals(carReturn.getId(), car.getId());
	}
	
	@Test
	public void getCar_notNull_test() throws CarException {	
		when(carService.getCarById(1l)).thenReturn(new Car());
		Car car = carController.getCar(1l);
		assertNotNull(car);
	}
	
	@Test
	public void getCar_null_test() throws CarException {	
		when(carService.getCarById(1l)).thenReturn(null);
		Car car = carController.getCar(1l);
		assertNull(car);
	}
	
	@Test
	public void updateCar_test() throws CarException {	
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carService.saveCar(car)).thenReturn(car);
		Car saveCar = carController.saveCar(car);
		assertNotNull(saveCar);
	}
	
	@Test
	public void saveCar_null_test() throws CarException {	
		Car car = new Car();
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carService.saveCar(car)).thenReturn(car);
		Car saveCar = carController.saveCar(car);
		assertNotNull(saveCar);
	}
	
	@Test
	public void deleteCar_notNull_test() throws CarException {
		doNothing().when(carService).deleteCar(1l);
		ResponseEntity<Object> deleteCar = carController.deleteCar(1l);
		assertEquals(HttpStatus.OK.value(), deleteCar.getStatusCode().value());
	}
	
}
