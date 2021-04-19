package es.paradigma.cdc.component.mapper;

import es.paradigma.cdc.model.dto.Product;
import es.paradigma.cdc.model.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductToEntityMapper {
  
  public ProductEntity buildProduct(Product product) {
    return ProductEntity
      .builder()
      ._id(product.getId())
      .name(product.getName())
      .description(product.getDescription())
      .weight(product.getWeight())
      .build();
  }

}