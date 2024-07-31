package com.admin.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class OTPStorageService {
	
	
	 private ConcurrentHashMap<String, String> otpCache = new ConcurrentHashMap<>();

	    public void storeOTP(String email, String otp) {
	        otpCache.put(email, otp);
	    }

	    public boolean validateOTP(String email, String otp) {
	        return otp.equals(otpCache.get(email));
	    }

	    public void removeOTP(String email) {
	        otpCache.remove(email);
	    }

}
