package es.paradigma.cdc.repository;

import es.paradigma.cdc.model.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<ProductEntity, String>{}