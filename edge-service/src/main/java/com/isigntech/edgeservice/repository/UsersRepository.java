package com.isigntech.edgeservice.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isigntech.edgeservice.entity.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, String>{

	Optional<Users> findByEmail(String email);
	
	@Modifying
	@Query("update Users e set e.enabled = false where e.email = :email")
	public void deleteByEmail(@Param("email") String email);

}
