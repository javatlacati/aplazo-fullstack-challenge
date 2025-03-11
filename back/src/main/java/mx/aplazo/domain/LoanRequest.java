package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class LoanRequest {
  @JsonProperty("customerId")
  @Schema(required = true, description = "Customer's unique identifier")
  @Valid
  @NotNull
  private UUID customerId = null;

  @JsonProperty("amount")
  @Schema(required = true, description = "Requested loan amount")
  @NotNull
  @DecimalMin("0")
  private Double amount = null;
}
