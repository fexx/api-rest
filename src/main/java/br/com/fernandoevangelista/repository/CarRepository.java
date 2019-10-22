package br.com.fernandoevangelista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernandoevangelista.controller.model.Car;

/**
 * 
 * @author Fernando Evangelista
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
