package mx.aplazo.repository;

import mx.aplazo.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the Loan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {}
