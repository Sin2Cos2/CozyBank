package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.AccountTransactions;
import com.sin2cos2.springsecurity.repositories.AccountTransactionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/balance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
        return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
    }
}
