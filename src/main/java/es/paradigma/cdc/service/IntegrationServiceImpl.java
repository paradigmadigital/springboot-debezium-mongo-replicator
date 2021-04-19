package es.paradigma.cdc.service;

import es.paradigma.cdc.enums.SourceDatabaseEntity;
import es.paradigma.cdc.model.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static es.paradigma.cdc.utils.Consts.UNDEFINED_ENTITY_ERROR_MESSAGE;

@Slf4j
@Service
public class IntegrationServiceImpl implements IntegrationService {
  
  private final ManagementService managementService;
  
  public IntegrationServiceImpl(ManagementService managementService){
    this.managementService = managementService;
  }
  
  @Override
  public void processKafkaEvent(Event event){
      SourceDatabaseEntity entity = SourceDatabaseEntity.fromId(event.getPayload().getSource().getTable());
      switch (entity){
        case PRODUCT:
          managementService.manageProduct(event.getPayload());
          break;
        default:
          log.error(UNDEFINED_ENTITY_ERROR_MESSAGE);
      }
  }
  
}
