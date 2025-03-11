package mx.aplazo.service;

import java.util.Optional;
import java.util.UUID;

import mx.aplazo.model.Customer;
import mx.aplazo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service Interface for managing {@link Customer}. */
@Service
@Transactional
public class CustomerService {
  private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer save(Customer customer) {
    LOG.debug("Request to save Customer : {}", customer);
    return customerRepository.save(customer);
  }

  public Customer update(Customer customer) {
    LOG.debug("Request to update Customer : {}", customer);
    return customerRepository.save(customer);
  }

  public Optional<Customer> partialUpdate(Customer customer) {
    LOG.debug("Request to partially update Customer : {}", customer);

    return customerRepository
        .findById(customer.getId())
        .map(
            existingCustomer -> {
              if (customer.getName() != null) {
                existingCustomer.setName(customer.getName());
              }
              if (customer.getEmail() != null) {
                existingCustomer.setEmail(customer.getEmail());
              }
              if (customer.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(customer.getPhoneNumber());
              }

              return existingCustomer;
            })
        .map(customerRepository::save);
  }

  @Transactional(readOnly = true)
  public Page<Customer> findAll(Pageable pageable) {
    LOG.debug("Request to get all Customers");
    return customerRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Optional<Customer> findOne(UUID id) {
    LOG.debug("Request to get Customer : {}", id);
    return customerRepository.findById(id);
  }

  public void delete(UUID id) {
    LOG.debug("Request to delete Customer : {}", id);
    customerRepository.deleteById(id);
  }
}
