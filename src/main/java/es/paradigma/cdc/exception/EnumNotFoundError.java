package es.paradigma.cdc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EnumNotFoundError extends RuntimeException{
  private String message;
}
