package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InMemoryCarRepository implements CarRepository {
    private final List<Car> carData = new ArrayList<>();

    @Override
    public Car createCar(Car car) {
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Optional<Car> findById(String id) {
        return carData.stream()
                .filter(car -> car.getCarId().equals(id))
                .findFirst();
    }

    @Override
    public void update(String id, Car updatedCar) {
        findById(id).ifPresent(car -> {
            car.setCarName(updatedCar.getCarName());
            car.setCarColor(updatedCar.getCarColor());
            car.setCarQuantity(updatedCar.getCarQuantity());
        });
    }

    @Override
    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
