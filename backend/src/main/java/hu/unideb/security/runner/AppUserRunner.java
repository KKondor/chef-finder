package hu.unideb.security.runner;

import hu.unideb.security.model.AppUser;
import hu.unideb.security.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Order(0) // biztos, hogy a többi Runner előtt fusson
public class AppUserRunner implements CommandLineRunner {

  private final AppUserRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    if(repository.findByUsername("user").isEmpty()) {
      AppUser user = AppUser.builder()
        .username("user")
        .password(passwordEncoder.encode("pass"))
        .build();
      repository.save(user);
      System.out.println("Test user created: user / pass");
    }
  }
}

