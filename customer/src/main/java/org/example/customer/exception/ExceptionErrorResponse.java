package org.example.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionErrorResponse {

  private LocalDateTime timestamp = LocalDateTime.now();
  private String error;
  private List<Violation> violations;

}
