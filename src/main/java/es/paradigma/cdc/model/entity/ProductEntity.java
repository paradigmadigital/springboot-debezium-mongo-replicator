package es.paradigma.cdc.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity{
  @Id
  private String _id;
  @Field("name")
  private String name;
  @Field("description")
  private String description;
  @Field("weight")
  private double weight;
}