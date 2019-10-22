package br.com.fernandoevangelista.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernandoevangelista.controller.model.Car;
import br.com.fernandoevangelista.exception.CarException;
import br.com.fernandoevangelista.repository.CarRepository;

/**
 * Serviço responsavel pelas operações de carros
 * 
 * @author Fernando Evangelista
 *
 */
@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	/**
	 * Busca um carro por id
	 * @param id - Código unico do carro
	 * @return - Retorna um carro
	 * @throws CarException - Caso não tenha um carro com o id informado
	 */
	public Car getCarById(Long id) throws CarException {
		Optional<Car> car = carRepository.findById(id);
		if (car.isPresent()) {
			return car.get();
		}
		throw new CarException("Carro não existe com o id: "+id);
	}
	
	/**
	 * Busca todos os carros disponiveis
	 * @return - Lista de carros
	 */
	public List<Car> getCars() {
		return carRepository.findAll();
	}
	
	/**
	 * Salvar um carro
	 * Caso tenha um id ele atualiza, senão salva um novo carro
	 * @param car - Carro para criar ou salvar
	 * @return - Carro salvo no banco
	 * @throws CarException - Caso algum problema ocorrá
	 */
	@Transactional
	public Car saveCar(Car car) throws CarException {
		try {
			if (car.getId() != null) {
				Car carBD = getCarById(car.getId());
				car.setId(carBD.getId());
			}
			carRepository.save(car);			
			return car;
		} catch (Exception e) {
			throw new CarException("Erro ao salvar carro. " + e.getMessage());
		}
	}
	
	/**
	 * Deleta um carro
	 * @param id - id do carro que será deletado
	 * @throws CarException - Caso algum problema ocorrá
	 */
	@Transactional
	public void deleteCar(Long id) throws CarException {
		try {
			Car carBD = getCarById(id);
			carRepository.delete(carBD);			
		} catch (Exception e) {
			throw new CarException("Erro ao deletar carro. " + e.getMessage());
		}
	}

}
