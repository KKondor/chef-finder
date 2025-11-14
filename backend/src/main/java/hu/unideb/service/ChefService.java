package hu.unideb.service;

import hu.unideb.model.Chef;
import hu.unideb.model.SkillLevel;

import java.util.List;
import java.util.Optional;

public interface ChefService {

  List<Chef> getAllChefs();

  Optional<Chef> getChefById(Long id);

  Chef createChef(Chef chef);

  Chef updateChef(Chef chef);

  void deleteChefById(Long id);

  boolean existsById(Long id);

  List<Chef> searchChefs(String q);

  boolean existsByRestaurantId(Long id);
}
