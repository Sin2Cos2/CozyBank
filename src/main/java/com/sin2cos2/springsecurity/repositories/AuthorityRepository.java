package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    List<Authority> getAllByCustomer_CustomerId(int customerId);
}
