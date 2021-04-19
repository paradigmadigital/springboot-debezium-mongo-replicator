package es.paradigma.cdc.enums;

import es.paradigma.cdc.exception.EnumNotFoundError;
import lombok.Getter;

import java.util.Arrays;

import static es.paradigma.cdc.utils.Consts.ENUM_NOT_FOUND_ERROR_MESSAGE;

@Getter
public enum SourceDatabaseOperation {
  
  CREATE("c"),
  UPDATE("u"),
  DELETE("d");
  
  private final String id;
  
  SourceDatabaseOperation(final String id) {
    this.id = id;
  }
  
  public static SourceDatabaseOperation fromId(String id){
    return Arrays.stream(SourceDatabaseOperation.values())
      .filter(v -> v.id.equals(id))
      .findFirst()
      .orElseThrow( () -> new EnumNotFoundError(ENUM_NOT_FOUND_ERROR_MESSAGE));
  }
  
}
