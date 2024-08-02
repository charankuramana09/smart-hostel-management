package com.isigntech.edgeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.isigntech.edgeservice.interceptor.JwtRequestInterceptor;

@Service
public class EdgeServiceImpl implements EdgeService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${admin.service.url}")
	private String adminUrl;
	@Value("${payment.service.url}")
	private String paymentUrl;
	@Value("${hosteller.service.url}")
	private String hostellerUrl;
	

	@Override
	public void sendSuccessEmail(String email, String firstName,String lastName) {
		String uriString = UriComponentsBuilder.fromHttpUrl(adminUrl).path("/email/signupSuccessEmail")
				.queryParam("email", email).queryParam("firstName", firstName).queryParam("lastName", lastName).toUriString();

		String emptyBody = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(emptyBody, headers);
		restTemplate.exchange(uriString, HttpMethod.POST, requestEntity, Void.class);
	}
	
	@Override
	public String updateStatus(String paymentId, String userId) {
		String paymentStatusFromPaymentService = this.getPaymentStatusFromPaymentService(paymentId, userId);
		return this.updatePaymentStatusInHostellerService(userId, paymentStatusFromPaymentService);
	}
	@Override
	public String getPaymentStatusFromPaymentService(String paymentId,String userId){
		 String uriString = UriComponentsBuilder.fromHttpUrl(paymentUrl)
	                .path("/payment/getPaymentStatus")
	                .queryParam("paymentId", paymentId)
	                .queryParam("userId", userId)
	                .toUriString();

		String emptyBody = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(emptyBody, headers);
		return restTemplate.exchange(uriString, HttpMethod.GET, requestEntity, String.class).getBody();
	}
	public String updatePaymentStatusInHostellerService(String userId,String paymentStatus){
		String uriString = UriComponentsBuilder.fromHttpUrl(hostellerUrl).path("/user/updatePaymentStatus")
				.queryParam("userId", userId).queryParam("paymentStatus", paymentStatus).toUriString();

		String emptyBody = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(emptyBody, headers);
		 String update = restTemplate.exchange(uriString, HttpMethod.PUT, requestEntity, String.class).getBody();
		 return update;
	}
	
	

}
