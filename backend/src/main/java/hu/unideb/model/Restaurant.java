package hu.unideb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "RESTAURANT")
public class Restaurant {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String street;
  private String city;
  private String buildingNumber;
}
