package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Builder
@ToString
public class ErrorResponse {
  @JsonProperty("code")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  @Schema(description = "Internal error code")
  @Pattern(regexp = "^APZ\\d{6}$")
  private String code = null;

  @JsonProperty("error")
  @Schema(description = "Internal error name")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  private String error = null;

  @JsonProperty("timestamp")
  @Schema(description = "Unix timestamp")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  private Long timestamp = null;

  @JsonProperty("message")
  @Schema(description = "Error message or error description")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  private String message = null;

  @JsonProperty("path")
  @Schema(description = "The path where the error occurred")
  @JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
  private String path = null;
}
