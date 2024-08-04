package com.isigntech.edgeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isigntech.edgeservice.service.EdgeService;

@RestController
@RequestMapping("/edge")
@CrossOrigin(origins ="/*")
public class EdgeController {

	@Autowired
	private EdgeService edgeService;
	
	@PutMapping("/updatePayment")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> getPaymentStatus(@RequestParam("paymentId") String paymentId,
			@RequestParam("userId") String userId) {
		return ResponseEntity.ok(edgeService.updateStatus(paymentId, userId));
	}
}
