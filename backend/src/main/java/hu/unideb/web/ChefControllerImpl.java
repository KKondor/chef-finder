package hu.unideb.web;

import hu.unideb.model.Chef;
import hu.unideb.model.SkillLevel;
import hu.unideb.service.ChefService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chef")
@AllArgsConstructor
public class ChefControllerImpl implements ChefController {

  private final ChefService chefService;

  @Override
  @GetMapping
  public List<Chef> getAll() {
    return chefService.getAllChefs();
  }

  @Override
  public List<Chef> searchChefs(String q) {
      return chefService.searchChefs(q);
  }

  @Override
  @GetMapping("/{id}")
  public Chef getOne(@PathVariable @NonNull Long id) {
    return chefService.getChefById(id)
      .orElseThrow(() -> new RuntimeException("Chef not found with id: " + id));
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
    if (!chefService.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    chefService.deleteChefById(id);
    return ResponseEntity.noContent().build();
  }


  @Override
  @PostMapping("/add")
  public Chef createOne(@RequestBody @NonNull Chef chef) {
    return chefService.createChef(chef);
  }

  @Override
  @PutMapping("/{id}")
  public Chef updateOne(@PathVariable Long id, @RequestBody @NonNull Chef chef) {
    Chef existing = chefService.getChefById(id)
      .orElseThrow(() -> new RuntimeException("Chef not found: " + id));
    existing.setName(chef.getName());
    existing.setAge(chef.getAge());
    existing.setLevel(chef.getLevel());
    existing.setSpecialty(chef.getSpecialty());
    return chefService.updateChef(existing);
  }
}
