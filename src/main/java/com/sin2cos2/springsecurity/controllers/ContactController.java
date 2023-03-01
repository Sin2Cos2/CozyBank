package com.sin2cos2.springsecurity.controllers;

import com.sin2cos2.springsecurity.models.Contact;
import com.sin2cos2.springsecurity.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@RestController
public class ContactController {

    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    //@PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test'")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        Contact contact = contacts.get(0);
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        contact = contactRepository.save(contact);
        List<Contact> returnContacts = new ArrayList<>();
        returnContacts.add(contact);

        return returnContacts;
    }

    private String getServiceReqNumber() {
        Random random = new Random();

        return "SR" + (random.nextInt(999999999 - 9999) + 9999);
    }
}
