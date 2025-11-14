package hu.unideb.service;

import com.github.javafaker.Faker;
import hu.unideb.model.Chef;
import hu.unideb.model.SkillLevel;
import hu.unideb.model.Restaurant;
import hu.unideb.repository.ChefRepository;
import hu.unideb.repository.RestaurantRepository;
import hu.unideb.service.ChefService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChefServiceImpl implements ChefService {

  private final ChefRepository chefRepository;
  private final RestaurantRepository restaurantRepository;

  private static final Faker FAKER = new Faker();
  private static final Random RANDOM = new Random();

  @Override
  public List<Chef> getAllChefs() {
    return (List<Chef>) chefRepository.findAll();
  }

  @Override
  public Optional<Chef> getChefById(Long id) {
    return chefRepository.findById(id);
  }

  @Override
  public Chef createChef(Chef chef) {
    return chefRepository.save(chef);
  }

  @Override
  public Chef updateChef(Chef chef) {
    if (chef.getCompCode() == 0 || !chefRepository.existsById(chef.getCompCode())) {
      throw new RuntimeException("Chef not found with id: " + chef.getCompCode());
    }
    return chefRepository.save(chef);
  }

  @Override
  public void deleteChefById(Long id) {
    chefRepository.deleteById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return chefRepository.existsById(id);
  }

  @Override
  public List<Chef> searchChefs(String q) {
      if (q == null || q.isBlank()) {
          return (List<Chef>) chefRepository.findAll();
      }
      return chefRepository
              .findByNameContainingIgnoreCaseOrSpecialtyContainingIgnoreCase(q, q);
    }


  @Override
  public boolean existsByRestaurantId(Long id) {
    return chefRepository.existsByRestaurantId(id);
  }
}
