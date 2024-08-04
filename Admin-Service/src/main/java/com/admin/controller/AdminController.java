package com.admin.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.UserDetailsResponseDto;
import com.admin.entity.UserDetails;
import com.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	 private AdminService adminService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	public ResponseEntity<List<UserDetails>> findAllUserDetails(){
		
		return new ResponseEntity<List<UserDetails>>(adminService.findAllUsers(),HttpStatus.OK);
	}
	
	
	@GetMapping("/filter/hostelname")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	public ResponseEntity<List<Map<String, Object>>> getUserDetailsByHostelName(@RequestParam String hostelName) {
	    List<UserDetails> details = adminService.findByHostelName(hostelName);

	    List<Map<String, Object>> result = details.stream()
	            .map(userDetails -> {
	                Map<String, Object> map = new HashMap<>();
	                map.put("userId", userDetails.getUserId());
	                map.put("firstName", userDetails.getFirstName());
	                map.put("lastName", userDetails.getLastName());
	                map.put("gender", userDetails.getGender());
	                map.put("joiningDate", userDetails.getJoiningDate());
	                map.put("purpose", userDetails.getPurpose());
	                map.put("roomSharing", userDetails.getRoomSharing());
	                map.put("frequency", userDetails.getFrequency());
	                map.put("userType", userDetails.getUserType());
	                map.put("mobileNumber", userDetails.getMobileNumber());
	                map.put("alternateMobileNumber", userDetails.getAlternateMobileNumber());
	                map.put("email", userDetails.getEmail());
	                map.put("idProof", userDetails.getIdProof()); // Consider security implications
	                map.put("status", userDetails.isStatus());
	                map.put("paidAmount", userDetails.getPaidAmount());
	                map.put("pendingAmount", userDetails.getPendingAmount());
	                map.put("advancePayment", userDetails.getAdvancePayment());
	                map.put("hostelName", userDetails.getHostelName());
	                map.put("paymentETA", userDetails.getPaymentETA());
	                map.put("roomNumber", userDetails.getRoomNumber());
	                map.put("roomType", userDetails.getRoomType());
	                return map;
	            })
	            .collect(Collectors.toList());

	    return ResponseEntity.ok(result);
	}
	
	   @GetMapping("/filter")
	   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	    public ResponseEntity<List<Object[]>> getUserDetailsByFrequencyType(@RequestParam String frequencyType, @RequestParam String hostelName) {
	        List<Object[]> details = adminService.getUserDetailsByFrequencyType(frequencyType, hostelName);
	        return ResponseEntity.ok(details);
	    }
	   
	       
	    
	    @PatchMapping("/patch/{userId}")
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	    public ResponseEntity<UserDetailsResponseDto> patchUserDetails(@PathVariable long userId, @RequestBody Map<String, Object> updates) {
	        try {
	            UserDetailsResponseDto updatedUser = adminService.patchUserDetails(userId, updates);
	            return ResponseEntity.ok(updatedUser);
	        }catch (Exception e) {
				System.out.println(e);
			}
			return null;
	    }
	    
	    @GetMapping("/validate-mobile-numbers")
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	    public Map<String, Object> validateMobileNumbers(@RequestBody List<Long> mobileNumbers) {
	        return adminService.validateMobileNumbers(mobileNumbers);
	    }

	   
}  
	
