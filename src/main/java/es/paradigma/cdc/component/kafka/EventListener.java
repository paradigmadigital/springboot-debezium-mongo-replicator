package es.paradigma.cdc.component.kafka;

import es.paradigma.cdc.model.event.Event;
import es.paradigma.cdc.service.IntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class EventListener {
  
  private final IntegrationService integrationService;
  
  public EventListener (IntegrationService integrationService){
    this.integrationService = integrationService;
  }

  @KafkaListener(
    topics = {"${kafka.topic.product}"},
    groupId = "${kafka.group}",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void listenEvents(Event event){
    if (Objects.nonNull(event.getPayload().getSource())){
      log.info("New event consumed successfully");
      integrationService.processKafkaEvent(event);
    }else{
      log.error("The event has been discarded for not having the expected information");
    }
  }

}