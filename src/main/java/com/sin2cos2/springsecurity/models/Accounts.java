package com.sin2cos2.springsecurity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accounts {


    @Id
    private long accountNumber;

    private int customerId;
    private String accountType;
    private String branchAddress;
    private String createDt;
}