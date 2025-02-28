package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CarServiceImpl implements GenericService<Car> {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        return carRepository.createCar(car);
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEachRemaining(cars::add);
        return cars;
    }

    @Override
    public Optional<Car> findById(String id) {
        return carRepository.findById(id);
    }

    @Override
    public void update(String id, Car car) {
        carRepository.update(id, car);
    }

    @Override
    public void deleteById(String id) {
        carRepository.delete(id);
    }
}
