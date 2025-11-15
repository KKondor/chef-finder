package hu.unideb.service;

import hu.unideb.model.Chef;
import hu.unideb.repository.ChefRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChefServiceImpl implements ChefService {

  private final ChefRepository chefRepository;

  @Override
  public List<Chef> getAllChefs() {
    return chefRepository.findAll();
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
          return chefRepository.findAll();
      }
      return chefRepository
              .findByNameContainingIgnoreCaseOrSpecialtyContainingIgnoreCase(q, q);
    }


  @Override
  public boolean existsByRestaurantId(Long id) {
    return chefRepository.existsByRestaurantId(id);
  }
}
