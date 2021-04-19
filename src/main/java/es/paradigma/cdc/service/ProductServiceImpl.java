package es.paradigma.cdc.service;

import es.paradigma.cdc.component.mapper.ProductToEntityMapper;
import es.paradigma.cdc.model.dto.Product;
import es.paradigma.cdc.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
  
  private final ProductRepository productRepository;
  private final ProductToEntityMapper mapper;
  
  public ProductServiceImpl(ProductRepository productRepository, ProductToEntityMapper mapper){
    this.productRepository = productRepository;
    this.mapper = mapper;
  }
  
  @Override
  public void create(Product product){
    productRepository.save(mapper.buildProduct(product));
  }
  
  @Override
  public void delete(String productId){
    productRepository.deleteById(productId);
  }
 
}