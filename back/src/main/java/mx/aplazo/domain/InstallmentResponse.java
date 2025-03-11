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

import java.time.LocalDate;

@Validated
@Getter
@Setter
@Builder
public class InstallmentResponse {
  @JsonProperty("amount")
  @Schema(required = true, description = "Installment amount to be paid")
  @NotNull
  @DecimalMin("0")
  private Double amount = null;

  @JsonProperty("scheduledPaymentDate")
  @Schema(required = true, description = "Scheduled date for the installment to be")
  @Valid
  @NotNull
  private LocalDate scheduledPaymentDate = null;

  @JsonProperty("status")
  @Schema(required = true, description = "")
  @Valid
  @NotNull
  private InstallmentStatus status = null;
}
