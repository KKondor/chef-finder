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

import java.util.Random;

@Component
@AllArgsConstructor
@Order(1)
public class RestaurantRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantRunner.class);
  private static final Faker FAKER = new Faker();

  private final RestaurantRepository restaurantRepository;

  @Override
  public void run(String... args) throws Exception {
    for (int i = 0; i < 200; i++) {
      String city = FAKER.address().city();
      String street = FAKER.address().streetName();
      String blNumber = FAKER.address().buildingNumber();
      String name = FAKER.company().name();
      Restaurant restaurant = Restaurant.builder()
        .name(name)
        .street(street)
        .city(city)
        .buildingNumber(blNumber)
        .build();

      restaurantRepository.save(restaurant);
      LOGGER.info("Restaurant inserted: {}", restaurant);
    }

    LOGGER.info("Restaurant chefs inserted, count: {}", restaurantRepository.count());
  }
}
