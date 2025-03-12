package mx.aplazo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mx.aplazo.domain.InstallmentStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "installment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Installment {
  @Id @GeneratedValue private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "loan_id", nullable = false)
  private Loan loan;

  @Column(nullable = false)
  private double amount;

  @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
  private Instant scheduledPaymentDate;

  @Column(nullable = false)
  private InstallmentStatus status;

  @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
  private Instant createdAt;
}
