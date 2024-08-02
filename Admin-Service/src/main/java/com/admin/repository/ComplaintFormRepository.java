package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.ComplaintForm;

public interface ComplaintFormRepository extends JpaRepository<ComplaintForm, Long> {

}
