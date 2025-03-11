package mx.aplazo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.aplazo.domain.CustomerRequest;
import mx.aplazo.domain.CustomerResponse;
import mx.aplazo.model.Customer;
import mx.aplazo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

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
  public ResponseEntity<CustomerResponse> createCustomer(
      @RequestBody(
              description = "Customer request",
              content =
                  @Content(
                      schema = @Schema,
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      examples = {
                        @ExampleObject(
                            name = "Customer under 18",
                            description = "Under age customer",
                            value =
                                "{\"firstName\": \"Juan\", \"lastName\": \"López\", \"secondLastName\": \"Pérez\",\"dateOfBirth\": \"2009-11-02\"}"),
                        @ExampleObject(
                            name = "Customer accepted",
                            description = "Customer which age is in accepted range",
                            value =
                                "{\"firstName\": \"Pepe\",  \"lastName\": \"García\",  \"secondLastName\": \"Flores\",  \"dateOfBirth\": \"1998-07-21\"}")
                      }))
          CustomerRequest body) {
    Customer customer = new Customer();
    customer.setName(body.getFirstName()); // TODO review logic here
    Customer savedCustomer = customersService.save(customer);
    return new ResponseEntity<>(
        CustomerResponse.builder().id(savedCustomer.getId()).build(), HttpStatusCode.valueOf(200));
  }

  @GetMapping("/customer/{id}")
  @Operation(summary = "Get customer identified by customerId")
  public ResponseEntity<CustomerResponse> findById(
      @Parameter(
              in = ParameterIn.PATH,
              description = "Customer's unique identified",
              required = true)
          @PathVariable
          UUID id) {
    Optional<Customer> retrievedCustomer = customersService.findOne(id);
    return retrievedCustomer
        .map(
            customer ->
                new ResponseEntity<>(
                    CustomerResponse.builder().id(customer.getId()).build(),
                    HttpStatusCode.valueOf(200)))
        .orElse(new ResponseEntity<>(HttpStatusCode.valueOf(404)));
  }
}
