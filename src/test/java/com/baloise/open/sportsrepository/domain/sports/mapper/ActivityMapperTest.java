package com.baloise.open.sportsrepository.domain.sports.mapper;

import com.baloise.open.edw.infrastructure.kafka.model.ActivityDto;
import com.baloise.open.sportsrepository.domain.sports.Activity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityMapperTest {

  ActivityDto createActivityDto() {
    return ActivityDto.newBuilder()
        .setName("My exciting Activity")
        .setType("HIKE")
        .setTimezone("(GMT+01:00) Europe/Vienna")
        .setStartDate(1636059193L)
        .setMovingTime(6200)
        .setElapsedTime(6498)
        .build();
  }

  @Test
  void name() {
    final ActivityDto origin = createActivityDto();
    final Activity result = ActivityMapper.INSTANCE.of(origin);
    assertEquals(origin.getName(), result.getName());
    assertEquals(origin.getType(), result.getType());
    assertEquals(origin.getTimezone(), result.getTimezone());
    assertEquals(origin.getStartDate(), result.getStartDate());
    assertEquals(origin.getMovingTime(), result.getMovingTime());
    assertEquals(origin.getElapsedTime(), result.getElapsedTime());
  }
}
