package com.isigntech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isigntech.Model.ComplaintForm;

public interface ComplaintFormRepository extends JpaRepository<ComplaintForm, Long> {

}
