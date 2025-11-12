package hu.unideb.service;

import hu.unideb.model.Restaurant;
import hu.unideb.repository.ChefRepository;
import hu.unideb.repository.RestaurantRepository;
import hu.unideb.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;

  @Override
  public List<Restaurant> getAllRestaurants() {
    return (List<Restaurant>) restaurantRepository.findAll();
  }

  @Override
  public Optional<Restaurant> getRestaurantById(Long id) {
    return restaurantRepository.findById(id);
  }

  @Override
  public Restaurant createRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Override
  public Restaurant updateRestaurant(Restaurant restaurant) {
    if (restaurant.getId() == null || !restaurantRepository.existsById(restaurant.getId())) {
      throw new RuntimeException("Restaurant not found with id: " + restaurant.getId());
    }
    return restaurantRepository.save(restaurant);
  }

  @Override
  public void deleteRestaurantById(Long id) {
    restaurantRepository.deleteById(id);
  }
}
