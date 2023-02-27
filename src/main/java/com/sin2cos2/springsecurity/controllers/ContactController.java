package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Contact;
import com.sin2cos2.springsecurity.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@AllArgsConstructor
@RestController
public class ContactController {

    private final ContactRepository contactRepository;

    @GetMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));

        return contactRepository.save(contact);
    }

    private String getServiceReqNumber() {
        Random random = new Random();

        return "SR" + (random.nextInt(999999999 - 9999) + 9999);
    }
}
