package com.baloise.open.sportsrepository.infrastructure.kafka;

import com.baloise.open.edw.infrastructure.kafka.Consumer;
import com.baloise.open.edw.infrastructure.kafka.model.ActivityDto;
import com.baloise.open.sportsrepository.domain.sports.Activity;
import com.baloise.open.sportsrepository.domain.sports.mapper.ActivityMapper;
import com.baloise.open.sportsrepository.infrastructure.db.sports.activity.ActivityEntity;
import com.baloise.open.sportsrepository.infrastructure.db.sports.activity.ActivityEntityMapper;
import com.baloise.open.sportsrepository.infrastructure.db.sports.activity.ActivityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

@Component
@Slf4j
public class ActivityConsumer {

  private final Config config;
  private final ActivityRepository activityRepository;

  private Consumer activityConsumer;

  public ActivityConsumer(@Autowired Config config, @Autowired ActivityRepository activityRepository) {
    this.config = config;
    this.activityRepository = activityRepository;
  }

  @PostConstruct
  public void initActivityProducer() {
    Properties specificProperties = new Properties();
    specificProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getActivityValueSerializer());
    specificProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, config.getActivityValueDeserializer());
    specificProperties.put(com.baloise.open.edw.infrastructure.kafka.Config.SCHEMA_SERVER_CONFIG_KEY, config.getSchemaRegistryUrl());
    specificProperties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, config.getSpecificAvroReader());

    activityConsumer = Consumer.create(specificProperties, config.getActivityTopicName(), config.getSystemId(), getActivityObjectHandler());
    activityConsumer.run();

    log.info("Start ActivityConsumer on topic " + config.getActivityTopicName());

  }

  private java.util.function.Consumer<? super ConsumerRecord<String, Object>> getActivityObjectHandler() {
    return (record) -> {
      try {
        final ActivityDto activityDto = new ObjectMapper().readValue(record.value().toString(), ActivityDto.class);

        // convert to Domain Object //TODO: may be done by stream inside kafka an not necessary here any longer
        final Activity activity = ActivityMapper.INSTANCE.of(activityDto);
        activity.setId(record.key());
        log.info("Domain object: " + activity);

        final ActivityEntity entity = ActivityEntityMapper.INSTANCE.of(activity);
        log.info("DB entity: " + entity);
        this.activityRepository.save(entity);

      } catch (JsonProcessingException e) {
        log.error(e.getMessage(), e);
        //TODO: Add event containing failure
      }
    };
  }

  @PreDestroy
  public void disconnectFromWorkflow() {
    activityConsumer.pushStatusShutdown();
  }
}
