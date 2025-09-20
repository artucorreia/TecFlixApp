package br.com.tecflix_app.sedders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoaderRunner implements CommandLineRunner {
  private final Seeder roleSeeder, genderSeeder, userSeeder, tagSeeder, occupationSeeder;

  public DataLoaderRunner(
      RoleSeeder roleSeeder,
      GenderSeeder genderSeeder,
      UserSeeder userSeeder,
      TagSeeder tagSeeder,
      OccupationSeeder occupationSeeder) {
    this.roleSeeder = roleSeeder;
    this.genderSeeder = genderSeeder;
    this.userSeeder = userSeeder;
    this.tagSeeder = tagSeeder;
    this.occupationSeeder = occupationSeeder;
  }

  @Override
  public void run(String... args) throws Exception {
    if (!Arrays.asList(args).contains("--load-data")) return;
    roleSeeder.run();
    userSeeder.run();
    genderSeeder.run();
    tagSeeder.run();
    occupationSeeder.run();
  }
}
