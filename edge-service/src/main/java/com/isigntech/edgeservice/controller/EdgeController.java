package com.isigntech.edgeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/edge")
public class EdgeController {
	
	 public void getPaymentStatus(@RequestParam("paymentId") String paymentId, @RequestParam("userId") String userId){
//	       paymentService.getPaymentStatus(paymentId, userId);
//	       hostell
	    }
}
