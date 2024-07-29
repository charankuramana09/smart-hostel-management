package com.isigntech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isigntech.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
