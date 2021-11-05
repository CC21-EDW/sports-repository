package com.baloise.open.sportsrepository.infrastructure.kafka;

import com.baloise.open.edw.infrastructure.kafka.Consumer;
import lombok.extern.slf4j.Slf4j;
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

  private Consumer activityConsumer;

  public ActivityConsumer(@Autowired Config config) {
    this.config = config;
  }

  @PostConstruct
  public void initActivityProducer() {
    Properties specificProperties = new Properties();
    specificProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getActivityValueSerializer());
    specificProperties.put(com.baloise.open.edw.infrastructure.kafka.Config.SCHEMA_SERVER_CONFIG_KEY, config.getSchemaRegistryUrl());
    activityConsumer = Consumer.create(specificProperties, config.getActivityTopicName(), config.getSystemId(), getActivityHandler());
    log.info("Start ActivityConsumer on topic " + config.getActivityTopicName());
    activityConsumer.run();
  }

  private java.util.function.Consumer<? super ConsumerRecord<String, String>> getActivityHandler() {
    return (record) -> {
      //TODO: TBI
      log.info("TBI: handler for" + record);
    };
  }

  @PreDestroy
  public void disconnectFromWorkflow() {
    activityConsumer.pushStatusProducerShutdown();
  }
}
