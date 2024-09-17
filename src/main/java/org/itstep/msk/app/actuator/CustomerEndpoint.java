package org.itstep.msk.app.actuator;

import lombok.RequiredArgsConstructor;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.CustomerRepository;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Custom endpoint of actuator for checking avaliable customers
 */
@Component
@Endpoint(id = "customers")
@RequiredArgsConstructor
public class CustomerEndpoint {

    private final CustomerRepository customerRepository;

    @ReadOperation
    public Iterable<Customer> customers() {
        return customerRepository.findAll();
    }

    @ReadOperation
    public Object selectCustomer(@Selector Integer customerId) {
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return String.format("No avaliable customer with  id %d", customerId);

    }

    @WriteOperation
    public void updateCustomerName(@Selector Integer customId, String name) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customId);
        if (!optionalCustomer.isEmpty()) {
            Customer customer = optionalCustomer.get();
            customer.setName(name);
            customerRepository.save(customer);
        }
    }

    @DeleteOperation
    public void deleteCustomerById(@Selector Integer id) {
        customerRepository.deleteById(id);
    }
}
