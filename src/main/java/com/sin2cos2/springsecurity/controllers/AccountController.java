package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Accounts;
import com.sin2cos2.springsecurity.repositories.AccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/account")
    public Accounts getAccountDetail(@RequestParam int id) {
        return accountsRepository.findByCustomerId(id);
    }
}
