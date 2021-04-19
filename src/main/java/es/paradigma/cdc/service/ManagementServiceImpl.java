package es.paradigma.cdc.service;

import es.paradigma.cdc.enums.SourceDatabaseOperation;
import es.paradigma.cdc.model.dto.Product;
import es.paradigma.cdc.model.event.EventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static es.paradigma.cdc.utils.Consts.UNDEFINED_OPERATION_ERROR_MESSAGE;


@Slf4j
@Service
public class ManagementServiceImpl implements ManagementService {
  
  private final ProductService productService;
  
  public ManagementServiceImpl(ProductService productService){
    this.productService = productService;
  }
  
  @Override
  public void manageProduct(EventPayload payload){
    SourceDatabaseOperation operation = SourceDatabaseOperation.fromId(payload.getOp());
    switch (operation){
      case CREATE:
      case UPDATE:
        Product product = Product
          .builder()
          .id(payload.getAfter().get("id").toString())
          .name(payload.getAfter().get("name").toString())
          .description(payload.getAfter().get("description").toString())
          .weight(Double.parseDouble(payload.getAfter().get("weight").toString()))
          .build();
        productService.create(product);
        break;
      case DELETE:
        String productId = payload.getAfter().get("id").toString();
        productService.delete(productId);
        break;
      default:
        log.error(UNDEFINED_OPERATION_ERROR_MESSAGE);
    }
  }
  
}
