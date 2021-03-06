package com.baloise.open.sportsrepository.infrastructure.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Config {
  @Value("${edw.kafka.system-id}")
  private String systemId;

  @Value("${edw.kafka.activity.topic-name}")
  private String activityTopicName;

  @Value("${edw.kafka.activity.value-serializer}")
  private String activityValueSerializer;

  @Value("${edw.kafka.activity.value-deserializer}")
  private String activityValueDeserializer;

  @Value("${edw.kafka.schema-registry-url}")
  private String schemaRegistryUrl;

  @Value("${edw.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${edw.kafka.activity.specific-avro-reader}")
  private Boolean specificAvroReader;

}
