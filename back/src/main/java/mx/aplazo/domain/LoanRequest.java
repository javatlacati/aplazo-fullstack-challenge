package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Getter
public class LoanRequest {
    @JsonProperty("customerId")

    private UUID customerId = null;

    @JsonProperty("amount")

    private Double amount = null;


    public LoanRequest customerId(UUID customerId) {

        this.customerId = customerId;
        return this;
    }
}
