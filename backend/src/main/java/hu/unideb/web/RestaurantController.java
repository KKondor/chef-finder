package hu.unideb.web;


import hu.unideb.model.Restaurant;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/restaurant")
public interface RestaurantController {

  @GetMapping
  List<Restaurant> getAll();

  @GetMapping("/search")
  List<Restaurant> searchRestaurants(@RequestParam(required = false) String q);

  @GetMapping("/{id:\\d+}")
  Restaurant getOne(@NonNull @PathVariable Long id);

  @DeleteMapping("/{id:\\d+}")
  void deleteById(@NonNull @PathVariable Long id);

  @PostMapping("/add")
  Restaurant createOne(@NonNull @RequestBody Restaurant restaurant);

  @PutMapping("/{id}")
  Restaurant updateOne(@NonNull @RequestBody Restaurant restaurant);
}
