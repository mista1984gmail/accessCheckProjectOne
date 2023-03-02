package org.example.customer.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(NoAccessException.class)
  protected ResponseEntity<ExceptionErrorResponse> handleNoAccessException(NoAccessException ex,
                                                                       WebRequest request) {

    return buildResponseAndLogError(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<ExceptionErrorResponse> buildResponseAndLogError(NoAccessException ex,
                                                                          HttpStatus status) {

    var response = new ExceptionErrorResponse();
    response.setError(ex.getMessage());
    log.error(ex.getMessage());
    return new ResponseEntity<>(response, status);
  }


}
