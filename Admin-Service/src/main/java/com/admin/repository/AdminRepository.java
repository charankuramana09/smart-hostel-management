package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.entity.UserDetails;

@Repository
public interface AdminRepository extends JpaRepository<UserDetails, Long> {
	
	List<UserDetails> findByHostelName(String hostelName);
    
    @Query("SELECT u.firstName, u.lastName, u.mobileNumber,u.joiningDate,u.paidAmount,u.pendingAmount,u.paymentETA, u.status  FROM UserDetails u WHERE u.frequency = :frequency AND u.hostelName = :hostelName")
    List<Object[]> findByFrequencyType(String frequency, String hostelName);
    
    
    @Query("SELECT u.mobileNumber FROM UserDetails u WHERE u.mobileNumber IN :mobileNumbers")
    List<Long> findExistingMobileNumbers(@Param("mobileNumbers")  List<Long> mobileNumbers);
    
}
