package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@Setter
@Getter
@Builder
public class LoanResponsePaymentPlan {
    @JsonProperty("commissionAmount")
    @Schema(required = true, description = "Commission amount applied to loan")
    @NotNull
    @DecimalMin("0")
    private Double commissionAmount = null;

    @JsonProperty("installments")
    @Schema(required = true, description = "")
    @Valid
    @NotNull
    @Size(min=5,max=5)
    private List<InstallmentResponse> installments = new ArrayList<InstallmentResponse>();
}
