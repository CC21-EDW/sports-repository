package com.baloise.open.sportsrepository;


import com.baloise.open.sportsrepository.infrastructure.kafka.ActivityConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {SportsRepositoryApplicationTests.Initializer.class})
class SportsRepositoryApplicationTests {

  @Autowired ActivityConsumer activityConsumer;

  @Container
  public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:11.2")
      .withDatabaseName("testdb")
      .withUsername("sa")
      .withPassword("sa");

  @Container
  public static KafkaContainer kafkaTestContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.3.2"))
      .withEmbeddedZookeeper();

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + postgres.getJdbcUrl(),
          "spring.datasource.username=" + postgres.getUsername(),
          "spring.datasource.password=" + postgres.getPassword(),
          "edw.kafka.bootstrap-servers=" + kafkaTestContainer.getBootstrapServers()
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  void contextLoads() {
    try {
      assertTrue(postgres.isRunning());
      assertTrue(kafkaTestContainer.isRunning());
      assertNotNull(activityConsumer);
    } finally {
      activityConsumer.disconnectFromWorkflow();
    }
  }

}
