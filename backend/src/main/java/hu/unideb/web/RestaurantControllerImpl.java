package hu.unideb.web;

import hu.unideb.model.Restaurant;
import hu.unideb.service.ChefService;
import hu.unideb.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

  private final RestaurantService restaurantService;
  private final ChefService chefService;

  @Override
  @GetMapping
  public List<Restaurant> getAll() {
    return restaurantService.getAllRestaurants();
  }

  @Override
  @GetMapping("/search")
  public List<Restaurant> search(Optional<String> city, Optional<String> street) {
    return restaurantService.getAllRestaurants().stream()
      .filter(r -> city.map(c -> r.getCity().toLowerCase().contains(c.toLowerCase())).orElse(true))
      .filter(r -> street.map(s -> r.getStreet().toLowerCase().contains(s.toLowerCase())).orElse(true))
      .collect(Collectors.toList());
  }

  @Override
  @GetMapping("/{id}")
  public Restaurant getOne(@PathVariable Long id) {
    return restaurantService.getRestaurantById(id)
      .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
      if (chefService.existsByRestaurantId(id)) {
        // Throws 400 Bad Request instead of 500
        throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Cannot delete restaurant with chefs assigned"
        );
      }
      restaurantService.deleteRestaurantById(id);
    }

  @Override
  @PostMapping
  public Restaurant createOne(@RequestBody Restaurant restaurant) {
    return restaurantService.createRestaurant(restaurant);
  }

  @Override
  @PutMapping("/{id}")
  public Restaurant updateOne(@RequestBody Restaurant restaurant) {
    return restaurantService.updateRestaurant(restaurant);
  }
}
