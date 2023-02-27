package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByEmail(String email);
}
