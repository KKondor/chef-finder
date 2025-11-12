package hu.unideb.service;

import hu.unideb.model.Chef;
import hu.unideb.model.SkillLevel;

import java.util.List;
import java.util.Optional;

public interface ChefService {

  List<Chef> getAllChefs();

  List<Chef> searchChefs(Optional<String> name, Optional<SkillLevel> level, Optional<String> specialty);

  Optional<Chef> getChefById(Long id);

  Chef createChef(Chef chef);

  Chef updateChef(Chef chef);

  void deleteChefById(Long id);

  boolean existsById(Long id);

  Chef createRandomChef();

  boolean existsByRestaurantId(Long id);
}
