package com.baloise.open.sportsrepository.infrastructure.db.sports.activity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActivityRepository extends CrudRepository<ActivityEntity, String> {
}
