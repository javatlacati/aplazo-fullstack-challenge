package mx.aplazo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.aplazo.model.Customer;
import mx.aplazo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Tag(name = "Customers", description = "Manage customers")
public class CustomerController {
  @Autowired private CustomerService customersService;

  @PostMapping("/customers")
  @Operation(summary = "Create a customer")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Created"),
      })
  public Customer createCustomer(@RequestBody(description = "Customer request") Customer customer) {
    return customersService.save(customer);
  }

  @GetMapping("/customer/{id}")
  @Operation(summary = "Get customer identified by customerId")
  public Customer findById(@PathVariable Long id) {
    return customersService
        .findOne(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
  }
}
