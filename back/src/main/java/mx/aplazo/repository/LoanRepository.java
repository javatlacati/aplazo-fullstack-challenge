package mx.aplazo.repository;

import mx.aplazo.model.Loan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Loan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {}
