package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Getter
@Setter
@Builder
public class CustomerRequest {
  @JsonProperty("firstName")
  @Schema(required = true, description = "Customer's first name")
  @NotNull
  private String firstName = null;

  @JsonProperty("lastName")
  @Schema(required = true, description = "Customer's last name")
  @NotNull
  private String lastName = null;

  @JsonProperty("secondLastName")
  @Schema(required = true, description = "Customer's second last name")
  @NotNull
  private String secondLastName = null;

  @JsonProperty("dateOfBirth")
  @Schema(
      required = true,
      description =
          "Customer's date of birth. Customers age should be at least 18 and 65 at most (at the date of sign up). ")
  @Valid
  @NotNull
  private LocalDate dateOfBirth = null;
}
