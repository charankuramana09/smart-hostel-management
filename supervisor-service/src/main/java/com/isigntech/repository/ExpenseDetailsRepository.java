package com.isigntech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isigntech.entity.ExpenseDetails;

@Repository
public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetails, Long>{

}
