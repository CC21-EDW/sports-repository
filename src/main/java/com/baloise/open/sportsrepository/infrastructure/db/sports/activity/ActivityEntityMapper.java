package com.baloise.open.sportsrepository.infrastructure.db.sports.activity;

import com.baloise.open.sportsrepository.domain.sports.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityEntityMapper {

  ActivityEntityMapper INSTANCE = Mappers.getMapper(ActivityEntityMapper.class);

  ActivityEntity of(Activity activity);

}
