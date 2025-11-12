package hu.unideb.web;

import hu.unideb.model.Chef;
import hu.unideb.model.SkillLevel;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chef")
public interface ChefController {

  @GetMapping
  List<Chef> getAll();

  @GetMapping("/search")
  List<Chef> search(
    @NonNull @RequestParam Optional<String> name,
    @NonNull @RequestParam Optional<SkillLevel> level,
    @NonNull @RequestParam Optional<String> specialty
  );

  @GetMapping("/{id}")
  Chef getOne(@NonNull @PathVariable Long id);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteById(@NonNull @PathVariable Long id);

  @PostMapping
  Chef createOne(@NonNull @RequestBody Chef chef);

  @PutMapping("/{id}")
  Chef updateOne(@PathVariable Long id,@NonNull @RequestBody Chef chef);
}
