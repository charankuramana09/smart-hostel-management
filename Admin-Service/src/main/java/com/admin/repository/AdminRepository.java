package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.UserDetails;

@Repository
public interface AdminRepository extends JpaRepository<UserDetails, Long> {
	
	@Query("SELECT p.firstName, p.lastName, p.mobileNumber,p.joiningDate,p.paidAmount,p.pendingAmount,p.paymentETA, p.status  FROM UserDetails p WHERE p.frequency = :frequency AND p.hostelName = :hostelName")
    List<Object[]> findByFrequencyType(String frequency, String hostelName);
    
    
}
