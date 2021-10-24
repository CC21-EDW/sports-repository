package com.baloise.open.sportsrepository;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {SportsRepositoryApplicationTests.Initializer.class})
class SportsRepositoryApplicationTests {

  @Container
  public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:11.2")
      .withDatabaseName("testdb")
      .withUsername("sa")
      .withPassword("sa");

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + postgres.getJdbcUrl(),
          "spring.datasource.username=" + postgres.getUsername(),
          "spring.datasource.password=" + postgres.getPassword(),
          "spring.flyway.schemas=sports",
          "spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl"
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  void contextLoads() {
    assertTrue(postgres.isRunning());
  }

}
