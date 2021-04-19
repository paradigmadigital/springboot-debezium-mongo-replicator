package es.paradigma.cdc.service;

import es.paradigma.cdc.model.event.Event;

public interface IntegrationService {

  void processKafkaEvent(Event event);
  
}
