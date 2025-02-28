package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;
import java.util.Optional;

public interface CarRepository {
    Car createCar(Car car);
    Iterator<Car> findAll();
    Optional<Car> findById(String id);
    void update(String id, Car updatedCar);
    void delete(String id);
}
