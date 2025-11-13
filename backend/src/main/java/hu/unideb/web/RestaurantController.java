package hu.unideb.web;

import hu.unideb.model.Restaurant;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
public interface RestaurantController {

  @GetMapping
  List<Restaurant> getAll();

  @GetMapping("/search")
  List<Restaurant> search(
    @NonNull @RequestParam Optional<String> city,
    @NonNull @RequestParam Optional<String> street
  );

  @GetMapping("/{id}")
  Restaurant getOne(@NonNull @PathVariable Long id);

  @DeleteMapping("/{id}")
  void deleteById(@NonNull @PathVariable Long id);

  @PostMapping("/add")
  Restaurant createOne(@NonNull @RequestBody Restaurant restaurant);

  @PutMapping("/{id}")
  Restaurant updateOne(@NonNull @RequestBody Restaurant restaurant);
}
