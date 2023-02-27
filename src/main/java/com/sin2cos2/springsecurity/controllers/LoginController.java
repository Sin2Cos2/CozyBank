package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Customer;
import com.sin2cos2.springsecurity.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LoginController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer savedCustomer;
        ResponseEntity<String> response = null;

        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getCustomerId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + ex.getMessage());
        }

        return response;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customerList = customerRepository.findByEmail(authentication.getName());

        return customerList.isEmpty() ? null : customerList.get(0);
    }
}
