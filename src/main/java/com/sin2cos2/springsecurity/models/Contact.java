package com.sin2cos2.springsecurity.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "contact_messages")
public class Contact {

    @Id
    private String contactId;
    
    private String contactName;
    private String contactEmail;
    private String subject;
    private String message;
    private Date createDt;
}
