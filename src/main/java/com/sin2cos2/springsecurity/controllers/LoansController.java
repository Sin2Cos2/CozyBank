package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Loans;
import com.sin2cos2.springsecurity.repositories.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/loans")
    public List<Loans> getLoanDetails(@RequestParam int id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
