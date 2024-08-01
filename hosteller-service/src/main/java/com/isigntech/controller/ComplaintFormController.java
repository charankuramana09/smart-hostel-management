package com.isigntech.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isigntech.ResponseDto.ComplaintFormResponseDTO;
import com.isigntech.Service.ComplaintFormService;

@RestController
@RequestMapping("/complaint")
public class ComplaintFormController {
	
	@Autowired
	ComplaintFormService complaintFormService;
	
	@PostMapping("/raiseTicket")
	@PreAuthorize("hasRole('ROLE_USER')")
	ResponseEntity<ComplaintFormResponseDTO> raiseComplaintFromUser(@RequestParam ("complaintForm")  String complaintFormResponseDTO,@RequestParam("supportingDocument") MultipartFile file) throws Exception, IOException{
		ComplaintFormResponseDTO formResponseDTO = new ObjectMapper().readValue(complaintFormResponseDTO, ComplaintFormResponseDTO.class);
		formResponseDTO.setSupportingDocument(file.getBytes());
		
		return new ResponseEntity<ComplaintFormResponseDTO>(complaintFormService.raiseComplaint(formResponseDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/findById{complaintId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR','ROLE_USER')")
	ResponseEntity<ComplaintFormResponseDTO> getComplaintById(@PathVariable long complaintId){
		
		return new ResponseEntity<ComplaintFormResponseDTO>(complaintFormService.getById(complaintId), HttpStatus.OK);
	}
 
	
	 
}
