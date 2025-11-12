package hu.unideb.model;

import java.util.Random;

public enum SkillLevel {
  JUNIOR,
  SENIOR,
  MASTER;

  private static final Random RANDOM = new Random();
  public static SkillLevel getRandom() {
    final var length = RANDOM.nextInt(values().length);
    return values()[length];
  }
}
