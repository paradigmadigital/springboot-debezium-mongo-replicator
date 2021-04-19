package es.paradigma.cdc.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPayload {
  
  private EventSource source;
  
  private String op;
  
  private HashMap<String,Object> before;
  
  private HashMap<String,Object> after;
  
}
