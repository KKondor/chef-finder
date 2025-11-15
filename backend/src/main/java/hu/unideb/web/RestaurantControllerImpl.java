package hu.unideb.web;

import hu.unideb.model.Restaurant;
import hu.unideb.service.ChefService;
import hu.unideb.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public List<Restaurant> searchRestaurants(String q) {
        return restaurantService.searchRestaurants(q);
    }

  @Override
  @GetMapping("/{id}")
  public Restaurant getOne(@PathVariable @NonNull Long id) {
    return restaurantService.getRestaurantById(id)
      .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable @NonNull Long id) {
      if (chefService.existsByRestaurantId(id)) {
        throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Cannot delete restaurant with chefs assigned"
        );
      }
      restaurantService.deleteRestaurantById(id);
    }

  @Override
  @PostMapping("/add")
  public Restaurant createOne(@RequestBody @NonNull Restaurant restaurant) {
    return restaurantService.createRestaurant(restaurant);
  }

  @Override
  @PutMapping("/{id}")
  public Restaurant updateOne(@RequestBody @NonNull Restaurant restaurant) {
    return restaurantService.updateRestaurant(restaurant);
  }
}
