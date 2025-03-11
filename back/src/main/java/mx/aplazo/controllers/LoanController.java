package mx.aplazo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.aplazo.domain.LoanRequest;
import mx.aplazo.domain.LoanResponse;
import mx.aplazo.model.Loan;
import mx.aplazo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
@Tag(name = "Loans", description = "Manage loans")
public class LoanController {
  @Autowired private LoanService loanService;

  @PostMapping("/loans")
  @Operation(summary = "Create a loan")
  public ResponseEntity<LoanResponse> createLoan(
      @RequestBody(description = "Loan request") LoanRequest loanData) {
    Loan loan = new Loan();
    loan.setAmount(loanData.getAmount());
    Loan createdLoan = loanService.save(loan);
    return new ResponseEntity<>(
        LoanResponse.builder().customerId(loanData.getCustomerId()).build(),
        HttpStatusCode.valueOf(201));
  }

  @GetMapping("/loans/{id}")
  @Operation(summary = "Get loan identified by loanId")
  public Loan getLoanById(@PathVariable UUID id) {
    return loanService
        .findOne(id)
        .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
  }
}
