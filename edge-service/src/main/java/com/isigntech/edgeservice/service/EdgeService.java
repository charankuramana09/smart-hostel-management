package com.isigntech.edgeservice.service;

import java.util.Map;

public interface EdgeService {
	public void sendSuccessEmail(String email,String firstname,String lastname);
	public String getPaymentStatusFromPaymentService(String paymentId,String userId);
	public Map<String, String> updateStatus(String paymentId, String userId);
}
