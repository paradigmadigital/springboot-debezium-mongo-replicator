package es.paradigma.cdc.service;

import es.paradigma.cdc.model.event.EventPayload;

public interface ManagementService {
  
  void manageProduct(EventPayload payload);
  
}
