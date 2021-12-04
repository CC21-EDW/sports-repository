[![CI](https://github.com/CC21-EDW/sports-repository/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/CC21-EDW/sports-repository/actions/workflows/ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=CC21-EDW_sports-repository&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=CC21-EDW_sports-repository)
[![DepShield Badge](https://depshield.sonatype.org/badges/CC21-EDW/sports-repository/depshield.svg)](https://depshield.github.io)
# sports-repository
Provides a repository to handle persistence for all kinds of sports information (events, athlete, etc.)

## Maven Build 
- Run test considering [application-ci.yml](./src/test/resources/application-ci.yaml)
  `mvn test -Dspring.profiles.active=ci`
- Avro schema download not possible during CI and can be disabled by maven profile `github-ci` 
  `mvn verify -P github-ci`
- Release `mvn release:prepare -B -P github-ci`

# References
* [Build a Basic App with Spring Boot and JPA using PostgreSQL](https://developer.okta.com/blog/2018/12/13/build-basic-app-spring-boot-jpa)
* [Build a Spring Boot App With Flyway and Postgres](https://dzone.com/articles/build-a-spring-boot-app-with-flyway-and-postgres)
* [Baloise Stock-Exchange-Broker](https://bitbucket.balgroupit.com/projects/KAFKA/repos/stockexchange-broker/browse)
