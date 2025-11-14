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

  @GetMapping("/{id:\\d+}")
  Chef getOne(@NonNull @PathVariable Long id);

  @GetMapping("search")
  List<Chef> searchChefs(@RequestParam(required = false) String q);

  @DeleteMapping("/{id:\\d+}")
  ResponseEntity<Void> deleteById(@NonNull @PathVariable Long id);

  @PostMapping("/add")
  Chef createOne(@NonNull @RequestBody Chef chef);

  @PutMapping("/{id}")
  Chef updateOne(@PathVariable Long id,@NonNull @RequestBody Chef chef);
}
