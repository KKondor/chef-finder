package hu.unideb.security.config;

import hu.unideb.security.config.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final AppUserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
              .requestMatchers(HttpMethod.GET, "/api/chef/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/restaurant/**").permitAll()

              .requestMatchers(HttpMethod.POST, "/api/chef/add").authenticated()
              .requestMatchers(HttpMethod.PUT, "/api/chef/**").authenticated()
              .requestMatchers(HttpMethod.DELETE, "/api/chef/**").authenticated()

              .requestMatchers(HttpMethod.POST, "/api/restaurant/add").authenticated()
              .requestMatchers(HttpMethod.PUT, "/api/restaurant/**").authenticated()
              .requestMatchers(HttpMethod.DELETE, "/api/restaurant/**").authenticated()

              .anyRequest().authenticated()
      )
      .httpBasic(httpBasic -> {});
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
