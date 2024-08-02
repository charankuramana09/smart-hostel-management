package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.UserDetails;

@Repository
public interface AdminRepository extends JpaRepository<UserDetails, Long> {
	
	@Query("SELECT  u  FROM UserDetails u WHERE  u.hostelName = :hostelName")
    List<Object[]> findByHostelName( String hostelName);
    
    @Query("SELECT u.firstName, u.lastName, u.mobileNumber,u.joiningDate,u.paidAmount,u.pendingAmount,u.paymentETA, u.status  FROM UserDetails u WHERE u.frequency = :frequency AND u.hostelName = :hostelName")
    List<Object[]> findByFrequencyType(String frequency, String hostelName);
    
    
}
