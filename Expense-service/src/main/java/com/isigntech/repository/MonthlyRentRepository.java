package com.isigntech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isigntech.entity.MonthlyRent;
@Repository
public interface MonthlyRentRepository extends JpaRepository<MonthlyRent, Long>{

}
