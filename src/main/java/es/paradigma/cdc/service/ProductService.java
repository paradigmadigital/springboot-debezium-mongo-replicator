package es.paradigma.cdc.service;

import es.paradigma.cdc.model.dto.Product;

public interface ProductService {
  
  void create(Product product);
  
  void delete(String productId);
 
}