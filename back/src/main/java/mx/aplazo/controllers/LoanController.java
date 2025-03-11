package mx.aplazo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.aplazo.model.Loan;
import mx.aplazo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Tag(name = "Loans", description = "Manage loans")
public class LoanController {
  @Autowired private LoanService loanService;

  @PostMapping("/loans")
  @Operation(summary = "Create a loan")
  public Loan createLoan(Loan loanData) {
    return loanService.save(loanData);
  }

  @GetMapping("/loans/{id}")
  @Operation(summary = "Get loan identified by loanId")
  public Loan getLoanById(@PathVariable Long id) {
    return loanService
        .findOne(id)
        .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
  }
}
