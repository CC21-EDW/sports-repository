package com.baloise.open.sportsrepository.domain.sports.mapper;

import com.baloise.open.edw.infrastructure.kafka.model.ActivityDto;
import com.baloise.open.sportsrepository.domain.sports.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityMapper {

  ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

  Activity map(ActivityDto activityDto);
}
