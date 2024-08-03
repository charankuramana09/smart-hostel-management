package com.isigntech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isigntech.entity.EmployeeSalaries;

@Repository
public interface EmployeeSalariesRepository extends JpaRepository<EmployeeSalaries, Long>{

}
