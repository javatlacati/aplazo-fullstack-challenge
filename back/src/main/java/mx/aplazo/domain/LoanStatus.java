package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

/**
 * Statuses:
 *
 * <ul>
 *   <li>`ACTIVE`: has pending installment payments.
 *   <li>`LATE`: has installment payments with error.
 *   <li>`COMPLETED`: all installments are paid.
 * </ul>
 */
public enum LoanStatus {
  @JsonProperty("ACTIVE")
  ACTIVE("ACTIVE"),
  @JsonProperty("LATE")
  LATE("LATE"),
  @JsonProperty("COMPLETED")
  COMPLETED("COMPLETED");
  private final String value;

  LoanStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LoanStatus fromValue(String text) {
    for (LoanStatus b : LoanStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
