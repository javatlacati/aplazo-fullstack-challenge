package mx.aplazo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mx.aplazo.domain.ErrorResponse;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class AplazoException {
  private HttpStatus httpStatus;
  private ErrorResponse errorResponse;
}
