package com.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.ComplaintFormDTO;
import com.admin.service.ComplaintFormService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private ComplaintFormService  complaintFormService;
	
	@PutMapping("/update/{complaintId}")
	   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	    public ResponseEntity<ComplaintFormDTO> updateComplaintForm(
	            @RequestParam("complaintForm") String ComplaintFormDTO,
//	            @RequestParam("file") MultipartFile file,
	            @PathVariable long complaintId) throws Exception, IOException {

	        ComplaintFormDTO formResponseDTO = new ObjectMapper().readValue(ComplaintFormDTO, ComplaintFormDTO.class);
//	        formResponseDTO.setSupportingDocument(file.getBytes());

	        ComplaintFormDTO updatedFormResponseDTO = complaintFormService.updateUserComplaint(formResponseDTO, complaintId);
	        return new ResponseEntity<>(updatedFormResponseDTO, HttpStatus.OK);
	    }
	
}
