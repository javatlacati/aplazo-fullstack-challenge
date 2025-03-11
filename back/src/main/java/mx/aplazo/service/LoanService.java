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
              if (loan.getAmount() != null) {
                existingLoan.setAmount(loan.getAmount());
              }
              if (loan.getInterestRate() != null) {
                existingLoan.setInterestRate(loan.getInterestRate());
              }
              if (loan.getTermInMonths() != null) {
                existingLoan.setTermInMonths(loan.getTermInMonths());
              }
              if (loan.getStartDate() != null) {
                existingLoan.setStartDate(loan.getStartDate());
              }
              if (loan.getEndDate() != null) {
                existingLoan.setEndDate(loan.getEndDate());
              }
              if (loan.getCustomer() != null) {
                existingLoan.setCustomer(loan.getCustomer());
              }

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
