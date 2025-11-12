package hu.unideb.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity(name = "CHEFS")
public class Chef {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long compCode;
  private String name;
  private int age;
  @Enumerated(EnumType.STRING)
  private SkillLevel level;
  private String specialty;
  @ManyToOne
  private Restaurant restaurant;
}
