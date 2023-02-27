package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
