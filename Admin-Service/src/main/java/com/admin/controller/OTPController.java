package com.admin.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.exception.InvalidEmailFormatException;
import com.admin.exception.OtpMissMatchException;
import com.admin.service.impl.OTPService;
import com.admin.service.impl.OTPStorageService;
import com.admin.util.OTPUtil;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/otp")
public class OTPController {
	

    @Autowired
    private OTPService otpService;

    
    @Autowired
    private OTPStorageService otpStorageService;

    
    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email) {
    	
   	 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    
	    if (!matcher.matches()) {
	        throw new InvalidEmailFormatException("Invalid email format: " + email);
	    }
    	
        String otp = OTPUtil.generateOTP();
        try {
            otpService.sendOTP(email, otp);
            otpStorageService.storeOTP(email, otp);
            return "OTP sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send OTP";
        }
    }
    

    @PostMapping("/validate-otp")
    public String validateOTP(@RequestParam("email") String email, @RequestParam("otp") String otp) {
        if (otpStorageService.validateOTP(email, otp)) {
            otpStorageService.removeOTP(email);
            return "OTP validated successfully";
        } else {
           throw new OtpMissMatchException("Otp is Not Valid");
        }
    }


}
