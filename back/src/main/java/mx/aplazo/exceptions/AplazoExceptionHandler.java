package mx.aplazo.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import mx.aplazo.domain.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Hidden
public class AplazoExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    return buildResponseEntity(
        new AplazoException(
            HttpStatus.BAD_REQUEST,
            ErrorResponse.builder()
                .code("APZ000004")
                .error("INVALID_REQUEST")
                .message("Variable requerida faltante: " + ex.getVariableName())
                .path(request.getContextPath())
                .build()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    return buildResponseEntity(
        new AplazoException(
            HttpStatus.BAD_REQUEST,
            ErrorResponse.builder()
                .code("APZ000004")
                .error("INVALID_REQUEST")
                .message(
                    "Argumento inválido: "
                        + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .path(request.getContextPath())
                .build()));
  }

  public static ResponseEntity<Object> buildResponseEntity(AplazoException apiError) {
    return new ResponseEntity<>(apiError.getErrorResponse(), apiError.getHttpStatus());
  }
}
