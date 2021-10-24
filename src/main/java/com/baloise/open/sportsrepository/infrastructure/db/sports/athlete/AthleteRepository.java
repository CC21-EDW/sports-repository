package com.baloise.open.sportsrepository.infrastructure.db.sports.athlete;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AthleteRepository extends CrudRepository<AthleteEntity, String> {
}
