package hu.unideb.runner;

import com.github.javafaker.Faker;
import hu.unideb.model.Chef;
import hu.unideb.model.SkillLevel;
import hu.unideb.model.Restaurant;
import hu.unideb.repository.ChefRepository;
import hu.unideb.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
@Order(2)
public class ChefRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChefRunner.class);
  private static final Faker FAKER = new Faker();
  private static final Random RANDOM = new Random();

  private final ChefRepository chefRepository;
  private final RestaurantRepository restaurantRepository;
  @Override
  public void run(String... args) throws Exception {
    List<Restaurant> restaurants = restaurantRepository.findAll();
    for (int i = 0; i < 1000; i++) {
      String first = FAKER.name().firstName();
      String last = FAKER.name().lastName();
      String middle = FAKER.name().nameWithMiddle();


      String fullName = RANDOM.nextBoolean() ? first + " " + last : middle;

      Chef chef = Chef.builder()
        .name(fullName)
        .age(25 + RANDOM.nextInt(40)) // pl. 25-44 év
        .level(SkillLevel.getRandom())
        .specialty(FAKER.food().dish())
        .restaurant(restaurants.get(RANDOM.nextInt(restaurants.size())))
        .build();

      chefRepository.save(chef);
      LOGGER.info("Chef inserted: {}", chef);
    }

    LOGGER.info("All chefs inserted, count: {}", chefRepository.count());
  }
}
