package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;
import java.util.UUID;

@Validated
@Getter
@Setter
@Builder
public class CustomerResponse {
  @JsonProperty("id")
  @Schema(required = true, description = "Customer's unique identifier")
  @Valid
  @NotNull
  private UUID id = null;

  @JsonProperty("createdAt")
  @Schema(required = true, description = "Creation date time as ISO-8601")
  @Valid
  @NotNull
  private ZonedDateTime createdAt = null;

  @JsonProperty("creditLineAmount")
  @Schema(required = true, description = "Approved credit line")
  @NotNull
  @DecimalMin("0")
  private Double creditLineAmount = null;

  @JsonProperty("availableCreditLineAmount")
  @Schema(
      required = true,
      description = "Available credit line, discounting the amount used in loans.")
  @NotNull
  @DecimalMin("0")
  private Double availableCreditLineAmount = null;
}
