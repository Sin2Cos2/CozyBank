package com.sin2cos2.springsecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    private int customerId;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private Date createDt;
}
