package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    public CustomerService() { }

    private boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsPhoneNumber(phoneNumber);
    }

    public void add(Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException {

        if (existsByPhoneNumber(customer.getPhoneNumber()) || existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();

        else if (!isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();
        // else if (!isValidPassword()){}
        customerRepository.save(customer);
    }

    public void delete(String email) throws CustomerNotFoundException, InvalidCustomerException {
        Customer deleteMe;
        if (!ObjectUtils.isEmpty(email))
            if (existsByEmail(email)) {
                deleteMe = new Customer();
                deleteMe.setEmail(email);
                customerRepository.delete(deleteMe);
            }
            else throw new CustomerNotFoundException();
        else throw new InvalidCustomerException();
    }

    public void update(Customer customer, String email) throws CustomerNotFoundException, InvalidCustomerException, EmailNotSameException {
        if (!ObjectUtils.isEmpty(customer))
            if (existsByEmail(email) && existsByEmail(customer.getEmail()))
                if (email.equals(customer.getEmail()))
                    customerRepository.save(customer);
                 else throw new EmailNotSameException();
             else throw new CustomerNotFoundException(customer.getEmail());
         else throw new InvalidCustomerException();
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    private boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    private boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer getByEmail(String email) throws InvalidNameException, CustomerNotFoundException {
        if(!ObjectUtils.isEmpty(email)) {
            if (existsByEmail(email)) {
                return customerRepository.getByEmail(email);
            } else throw new CustomerNotFoundException();
        } else throw new InvalidNameException();
    }

    /*
    private void encryptPassword(Login login) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(encodedPassword);

        loginRepository.save(login);
    }
    */
}