package mx.aplazo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import mx.aplazo.domain.InstallmentResponse;
import mx.aplazo.domain.LoanRequest;
import mx.aplazo.domain.LoanResponse;
import mx.aplazo.domain.LoanResponsePaymentPlan;
import mx.aplazo.model.Customer;
import mx.aplazo.model.Loan;
import mx.aplazo.service.CustomerService;
import mx.aplazo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@Tag(name = "Loans", description = "Manage loans")
@Log
public class LoanController {
  @Value("${loan.comissionAmount:0.1}")
  double commissionAmount;

  @Autowired private LoanService loanService;
  @Autowired private CustomerService customerService;

  @PostMapping("/loans")
  @Operation(summary = "Create a loan")
  public ResponseEntity<LoanResponse> createLoan(
      @RequestBody(description = "Loan request") LoanRequest loanData) {
    log.fine("Creating loan for customer: " + loanData.getCustomerId());
    Optional<Customer> retrievedCustomer = customerService.findOne(loanData.getCustomerId());
    return retrievedCustomer
        .map(
            customer -> {
              Loan loan =
                  Loan.builder()
                      .customer(customer)
                      .amount(loanData.getAmount())
                      .createdAt(Instant.now())
                      .build();
              Loan createdLoan = loanService.save(loan);
              LoanResponsePaymentPlan loanResponsePaymentPlan =
                  LoanResponsePaymentPlan.builder()
                      .commissionAmount(commissionAmount)
                      .installments(
                          createdLoan.getInstallments().stream()
                              .map(
                                  installment ->
                                      InstallmentResponse.builder()
                                          .amount(installment.getAmount())
                                          .status(installment.getStatus())
                                          .scheduledPaymentDate(
                                              LocalDate.from(installment.getScheduledPaymentDate()))
                                          .build())
                              .collect(Collectors.toList()))
                      .build();
              return new ResponseEntity<>(
                  LoanResponse.builder()
                      .customerId(loanData.getCustomerId())
                      .id(createdLoan.getId())
                      .paymentPlan(loanResponsePaymentPlan)
                      .status(createdLoan.getStatus())
                      .createdAt(ZonedDateTime.from(createdLoan.getCreatedAt()))
                      .build(),
                  HttpStatusCode.valueOf(201));
            })
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/loans/{id}")
  @Operation(summary = "Get loan identified by loanId")
  public ResponseEntity<LoanResponse> getLoanById(
      @Parameter(in = ParameterIn.PATH, description = "Loan's unique identifier", required = true)
          @PathVariable
          UUID id) {
    log.fine("Retrieving loan with id: " + id);
    Optional<Loan> retrievedLoan = loanService.findOne(id);

    return retrievedLoan
        .map(
            loan -> {
              LoanResponsePaymentPlan paymentPlan =
                  LoanResponsePaymentPlan.builder()
                      .commissionAmount(commissionAmount)
                      .installments(
                          loan.getInstallments().stream()
                              .map(
                                  installment ->
                                      InstallmentResponse.builder()
                                          .amount(installment.getAmount())
                                          .status(installment.getStatus())
                                          .scheduledPaymentDate(
                                              LocalDate.from(installment.getScheduledPaymentDate()))
                                          .build())
                              .collect(Collectors.toList()))
                      .build();
              return new ResponseEntity<>(
                  LoanResponse.builder()
                      .customerId(loan.getCustomer().getId())
                      .status(loan.getStatus())
                      .createdAt(ZonedDateTime.from(loan.getCreatedAt()))
                      .id(loan.getId())
                      .paymentPlan(paymentPlan)
                      .build(),
                  HttpStatusCode.valueOf(200));
            })
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
