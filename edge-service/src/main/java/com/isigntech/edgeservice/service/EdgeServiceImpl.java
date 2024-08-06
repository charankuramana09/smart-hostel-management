package com.isigntech.edgeservice.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public Map<String, String> updateStatus(String paymentId, String userId) {
		Map<String, String> paymentStatusFromPaymentService = this.getPaymentStatusFromPaymentService(paymentId, userId);
		Map<String,String> map=new HashMap();
		map.put("update", this.updatePaymentStatusInHostellerService(userId, paymentStatusFromPaymentService));
		return map;
	}
	@Override
	public Map<String, String> getPaymentStatusFromPaymentService(String paymentId,String userId){
		 String uriString = UriComponentsBuilder.fromHttpUrl(paymentUrl)
	                .path("/payment/getPaymentStatus")
	                .queryParam("paymentId", paymentId)
	                .queryParam("userId", userId)
	                .toUriString();

		String emptyBody = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(emptyBody, headers);
		String responseBody = restTemplate.exchange(uriString, HttpMethod.GET, requestEntity, String.class).getBody();
		  ObjectMapper objectMapper = new ObjectMapper();
		    try {
		        return objectMapper.readValue(responseBody, new TypeReference<Map<String, String>>() {});
		    } catch (JsonProcessingException e) {
		        throw new RuntimeException("Failed to parse response body", e);
		    }
	}
	public String updatePaymentStatusInHostellerService(String userId,Map<String, String> paymentStatusFromPaymentService){
		
		String uriString = UriComponentsBuilder.fromHttpUrl(hostellerUrl).path("/user/updatePaymentStatus")
				.queryParam("userId", userId).queryParam("paymentInfo", encodeMapAsQueryParam(paymentStatusFromPaymentService)).toUriString();

		String emptyBody = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(emptyBody, headers);
		 String update = restTemplate.exchange(uriString, HttpMethod.PUT, requestEntity, String.class).getBody();
		 return update;
	}
	
	private String encodeMapAsQueryParam(Map<String, String> map) {
	    StringBuilder encodedParams = new StringBuilder();
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	        if (encodedParams.length() > 0) {
	            encodedParams.append("&");
	        }
	        encodedParams.append(UriUtils.encode(entry.getKey(), StandardCharsets.UTF_8))
	                     .append("=")
	                     .append(UriUtils.encode(entry.getValue(), StandardCharsets.UTF_8));
	    }
	    return encodedParams.toString();
	}
	
	

}
