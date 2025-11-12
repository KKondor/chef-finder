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
  public List<Chef> searchChefs(Optional<String> name, Optional<SkillLevel> level, Optional<String> specialty) {
    return getAllChefs().stream()
      .filter(c -> name.map(n -> c.getName().toLowerCase().contains(n.toLowerCase())).orElse(true))
      .filter(c -> level.map(l -> c.getLevel() == l).orElse(true))
      .filter(c -> specialty.map(s -> c.getSpecialty().toLowerCase().contains(s.toLowerCase())).orElse(true))
      .collect(Collectors.toList());
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
  public Chef createRandomChef() {
    String first = FAKER.name().firstName();
    String last = FAKER.name().lastName();
    String middle = FAKER.name().nameWithMiddle();
    String fullName = RANDOM.nextBoolean() ? first + " " + last : first + " " + middle + " " + last;

    int age = 25 + RANDOM.nextInt(20);
    SkillLevel level = SkillLevel.getRandom();
    String specialty = FAKER.food().dish();

    List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
    Restaurant restaurant = restaurants.get(RANDOM.nextInt(restaurants.size()));

    Chef chef = Chef.builder()
      .name(fullName)
      .age(age)
      .level(level)
      .specialty(specialty)
      .restaurant(restaurant)
      .build();

    return chefRepository.save(chef);
  }

  @Override
  public boolean existsByRestaurantId(Long id) {
    return chefRepository.existsByRestaurantId(id);
  }
}
