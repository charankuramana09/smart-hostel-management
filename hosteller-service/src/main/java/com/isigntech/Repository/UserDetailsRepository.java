package com.isigntech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isigntech.Model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

}
