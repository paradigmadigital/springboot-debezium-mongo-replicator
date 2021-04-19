package es.paradigma.cdc.component.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class KafkaErrorHandler implements ErrorHandler {
  
  @Override
  public void handle(Exception e, ConsumerRecord<?, ?> consumerRecord) {}
  
  @Override
  public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {}
  
  @Override
  public void handle(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer, MessageListenerContainer container) {
    
    log.error(thrownException.getMessage());
    
    String s = thrownException.getMessage().split("Error deserializing key/value for partition ")[1].split(". If needed, please seek past the record to continue consumption.")[0];
    String topics = s.substring(0, s.lastIndexOf('-'));
    int offset = Integer.parseInt(s.split("offset ")[1]);
    int partition = Integer.parseInt(s.substring(s.lastIndexOf('-') + 1).split(" at")[0]);
    
    TopicPartition topicPartition = new TopicPartition(topics, partition);
    
    log.error("Skipping " + topics + " - " + partition + " offset " + offset);
    // ignore event
    consumer.seek(topicPartition, offset + 1);
    
  }
  
}