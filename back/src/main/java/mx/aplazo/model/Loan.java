package mx.aplazo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/** A Loan. */
@Entity
@Table(name = "loan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@ToString
public class Loan implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue private UUID id;

  @NotNull
  @Column(name = "amount", nullable = false)
  private Double amount;

  @NotNull
  @Column(name = "interest_rate", nullable = false)
  private Double interestRate;

  @NotNull
  @Column(name = "term_in_months", nullable = false)
  private Integer termInMonths;

  @NotNull
  @Column(name = "start_date", nullable = false)
  private ZonedDateTime startDate;

  @NotNull
  @Column(name = "end_date", nullable = false)
  private ZonedDateTime endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnoreProperties(
      value = {"loans"},
      allowSetters = true)
  private Customer customer;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Customer)) {
      return false;
    }
    return getId() != null && getId().equals(((Customer) o).getId());
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }
}
