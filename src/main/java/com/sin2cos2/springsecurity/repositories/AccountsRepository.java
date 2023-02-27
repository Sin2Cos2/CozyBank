package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Accounts findByCustomerId(int customerId);
}
