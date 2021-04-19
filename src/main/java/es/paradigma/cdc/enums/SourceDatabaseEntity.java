package es.paradigma.cdc.enums;

import es.paradigma.cdc.exception.EnumNotFoundError;
import lombok.Getter;

import java.util.Arrays;
import static es.paradigma.cdc.utils.Consts.ENUM_NOT_FOUND_ERROR_MESSAGE;

@Getter
public enum SourceDatabaseEntity {
  
  PRODUCT("products");
  
  private String id;
  
  SourceDatabaseEntity(final String id) {
    this.id = id;
  }
  
  public static SourceDatabaseEntity fromId(String id){
    return Arrays.stream(SourceDatabaseEntity.values())
      .filter(v -> v.id.equals(id))
      .findFirst()
      .orElseThrow( () -> new EnumNotFoundError(ENUM_NOT_FOUND_ERROR_MESSAGE));
  }
  
}
