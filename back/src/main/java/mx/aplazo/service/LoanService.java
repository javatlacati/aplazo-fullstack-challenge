package mx.aplazo.service;

import mx.aplazo.model.Loan;
import mx.aplazo.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LoanService {
  private static final Logger LOG = LoggerFactory.getLogger(LoanService.class);

  private final LoanRepository loanRepository;

  public LoanService(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }

  public Loan save(Loan Loan) {
    LOG.debug("Request to save Loan : {}", Loan);
    return loanRepository.save(Loan);
  }

  public Loan update(Loan Loan) {
    LOG.debug("Request to update Loan : {}", Loan);
    return loanRepository.save(Loan);
  }

  public Optional<Loan> partialUpdate(Loan loan) {
    LOG.debug("Request to partially update loan : {}", loan);

    return loanRepository
        .findById(loan.getId())
        .map(
            existingLoan -> {
              if (loan.getCustomer() != null) {
                existingLoan.setCustomer(loan.getCustomer());
              }
              if (loan.getCreatedAt() != null) {
                existingLoan.setCreatedAt(loan.getCreatedAt());
              }
              if (loan.getStatus() != null) {
                existingLoan.setStatus(loan.getStatus());
              }
              if (loan.getInstallments() != null) {
                existingLoan.setInstallments(loan.getInstallments());
              }
              existingLoan.setAmount(loan.getAmount());

              return existingLoan;
            })
        .map(loanRepository::save);
  }

  @Transactional(readOnly = true)
  public Page<Loan> findAll(Pageable pageable) {
    LOG.debug("Request to get all Loans");
    return loanRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Optional<Loan> findOne(UUID id) {
    LOG.debug("Request to get Loan : {}", id);
    return loanRepository.findById(id);
  }

  public void delete(UUID id) {
    LOG.debug("Request to delete Loan : {}", id);
    loanRepository.deleteById(id);
  }
}
