package hu.unideb.security.config;

import hu.unideb.security.model.AppUser;
import hu.unideb.security.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

  private final AppUserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser user = repository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .roles("USER") // ez adja a Spring Security authorities-t
      .build();
  }

}
