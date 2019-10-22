package br.com.fernandoevangelista.service;

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
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.fernandoevangelista.controller.model.Car;
import br.com.fernandoevangelista.exception.CarException;
import br.com.fernandoevangelista.repository.CarRepository;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CarServiceTest {
	
	@InjectMocks
	private CarService carService;
	
	@Mock
	private CarRepository carRepository;
	
	@Test
	public void getCars_notNull_test() {	
		when(carRepository.findAll()).thenReturn(new ArrayList<>());
		List<Car> listCars = carService.getCars();
		assertNotNull(listCars);
	}
	
	@Test
	public void getCars_null_test() {	
		when(carRepository.findAll()).thenReturn(null);
		List<Car> listCars = carService.getCars();
		assertNull("Cars null", listCars);
	}
	
	@Test
	public void getCars_empty_test() {
		when(carRepository.findAll()).thenReturn(new ArrayList<>());
		List<Car> listCars = carService.getCars();
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
		when(carRepository.findAll()).thenReturn(cars);
		List<Car> listCars = carService.getCars();
		assertFalse(listCars.isEmpty());
	}
	
	@Test
	public void getCar_equals_test() throws CarException {	
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carRepository.findById(1l)).thenReturn(Optional.ofNullable(car));
		Car carReturn = carService.getCarById(1l);
		assertEquals(carReturn.getId(), car.getId());
	}
	
	@Test
	public void getCar_notNull_test() throws CarException {	
		when(carRepository.findById(1l)).thenReturn(Optional.ofNullable(new Car()));
		Car car = carService.getCarById(1l);
		assertNotNull(car);
	}
	
	@Test(expected = CarException.class)
	public void getCar_null_test() throws CarException {	
		when(carRepository.findById(1l)).thenReturn(Optional.ofNullable(null));
		carService.getCarById(1l);
	}
	
	@Test
	public void updateCar_test() throws CarException {	
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carRepository.findById(car.getId())).thenReturn(Optional.ofNullable(car));
		when(carRepository.save(car)).thenReturn(car);
		Car saveCar = carService.saveCar(car);
		assertNotNull(saveCar);
	}
	
	@Test(expected = CarException.class)
	public void updateCar_id_not_exist_test() throws CarException {	
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carRepository.save(car)).thenReturn(car);
		carService.saveCar(car);
	}
	
	@Test
	public void saveCar_null_test() throws CarException {	
		Car car = new Car();
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carRepository.save(car)).thenReturn(car);
		Car saveCar = carService.saveCar(car);
		assertNotNull(saveCar);
	}
	
	@Test
	public void deleteCar_notNull_test() throws CarException {
		Car car = new Car();
		car.setId(1l);
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carRepository.findById(car.getId())).thenReturn(Optional.ofNullable(car));
		doNothing().when(carRepository).delete(car);
		carService.deleteCar(1l);
		assertTrue("Sucess", true);
	}
	
	@Test(expected = CarException.class)
	public void deleteCar_id_not_exist_notNull_test() throws CarException {
		Car car = new Car();
		car.setColor("Branca");
		car.setModel("Mercedes");
		car.setPrice(new BigDecimal(1000));
		when(carRepository.findById(car.getId())).thenReturn(Optional.ofNullable(car));
		doNothing().when(carRepository).delete(car);
		carService.deleteCar(1l);
	}
	
}
