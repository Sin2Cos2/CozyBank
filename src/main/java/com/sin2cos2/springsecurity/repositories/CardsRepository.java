package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

    List<Cards> findByCustomerId(int customerId);
}
