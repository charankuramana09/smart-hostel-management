package com.isigntech.edgeservice.service;

public interface EdgeService {
	public void sendSuccessEmail(String email,String firstname,String lastname);
	public String getPaymentStatusFromPaymentService(String paymentId,String userId);
	public String updateStatus(String paymentId, String userId);
}
