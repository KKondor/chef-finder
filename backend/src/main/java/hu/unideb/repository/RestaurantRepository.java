package hu.unideb.repository;

import hu.unideb.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(String name, String city);
    List<Restaurant> findAll();
}
