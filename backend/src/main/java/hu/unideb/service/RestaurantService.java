package hu.unideb.service;

import hu.unideb.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

  List<Restaurant> getAllRestaurants();

  Optional<Restaurant> getRestaurantById(Long id);

  Restaurant createRestaurant(Restaurant restaurant);

  Restaurant updateRestaurant(Restaurant restaurant);

  void deleteRestaurantById(Long id);
}
