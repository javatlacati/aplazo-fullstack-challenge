package mx.aplazo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InstallmentStatus {
  @JsonProperty("NEXT") NEXT("NEXT"),
  @JsonProperty("PENDING") PENDING("PENDING"),
  @JsonProperty("ERROR") ERROR("ERROR");

  private String value;

  InstallmentStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static InstallmentStatus fromValue(String text) {
    for (InstallmentStatus b : InstallmentStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
