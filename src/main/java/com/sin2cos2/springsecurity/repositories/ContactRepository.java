package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {


}
