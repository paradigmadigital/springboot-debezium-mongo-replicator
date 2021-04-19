package es.paradigma.cdc.configuration;

import es.paradigma.cdc.component.kafka.KafkaErrorHandler;
import es.paradigma.cdc.model.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "kafka")
public class KafkaConsumerConfiguration {

  private String host;
  private String group;

  @Bean
  public ConsumerFactory<String, Event> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    log.info("HOST KAFKA: " + host);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Event.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setErrorHandler(new KafkaErrorHandler());
    return factory;
  }

}