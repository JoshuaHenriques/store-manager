package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerNotFoundException;
import org.jayhenri.ecommerce.exception.EmailNotSameException;
import org.jayhenri.ecommerce.exception.InvalidCustomerException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.validation.Valid;
import java.util.List;

@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PutMapping(value = "/updateCustomer/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable String email) throws InvalidCustomerException, CustomerNotFoundException, EmailNotSameException {
        customerService.update(customer, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteCustomer/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String email) throws InvalidCustomerException, CustomerNotFoundException {
        customerService.delete(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getByEmail(@Valid @PathVariable String email) throws InvalidNameException, CustomerNotFoundException {
        return customerService.getByEmail(email);
    }

    @GetMapping(value = "/listCustomers")
    public List<Customer> listInventory() {
        return customerService.findAll();
    }
}