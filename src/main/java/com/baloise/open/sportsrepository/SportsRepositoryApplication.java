package com.baloise.open.sportsrepository;

import com.baloise.open.sportsrepository.infrastructure.db.sports.activity.ActivityEntity;
import com.baloise.open.sportsrepository.infrastructure.db.sports.activity.ActivityRepository;
import com.baloise.open.sportsrepository.infrastructure.db.sports.athlete.AthleteEntity;
import com.baloise.open.sportsrepository.infrastructure.db.sports.athlete.AthleteRepository;
import com.baloise.open.sportsrepository.infrastructure.kafka.ActivityConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class SportsRepositoryApplication {

  @Autowired
  private ActivityConsumer activityConsumer;

  public static void main(String[] args) {
    SpringApplication.run(SportsRepositoryApplication.class, args);
  }


  @Bean
  ApplicationRunner initAthletes(AthleteRepository repository) {
    return args -> repository.save(AthleteEntity.builder()
        .id(UUID.randomUUID().toString())
        .firstName("Arthur")
        .lastName("Neudeck")
        .build());
  }

  @Bean
  ApplicationRunner initActivities(ActivityRepository repository) {
    return args ->
        repository.save(ActivityEntity.builder()
            .id(UUID.randomUUID().toString())
            .name("Reverse Lange Erle")
            .distance(BigDecimal.valueOf(6.67))
            .build());
  }
}
