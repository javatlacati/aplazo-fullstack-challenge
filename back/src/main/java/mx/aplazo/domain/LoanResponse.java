package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;
import java.util.UUID;

@Validated
@Setter
@Getter
@Builder
public class LoanResponse {
  @JsonProperty("id")
  @Schema(required = true, description = "Loan's unique identifier")
  @Valid
  @NotNull
  private UUID id = null;

  @JsonProperty("customerId")
  @Schema(required = true, description = "Customer's unique identifier")
  @Valid
  @NotNull
  private UUID customerId = null;

  @JsonProperty("status")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  @Schema(description = "")
  @Valid
  private LoanStatus status = null;

  @JsonProperty("createdAt")
  @Schema(required = true, description = "Creation date time as ISO-8601")
  @Valid
  @NotNull
  private ZonedDateTime createdAt = null;

  @JsonProperty("paymentPlan")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  @Schema(description = "")
  @Valid
  private LoanResponsePaymentPlan paymentPlan = null;
}
