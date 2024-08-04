package com.isigntech.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.isigntech.Model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE UserDetails p SET p.paymentStatus = :newPaymentStatus WHERE p.userId = :userId")
	void updateByUserId(Long userId, String newPaymentStatus);
	
	 UserDetails findByEmail(String email);

}
