package com.isigntech.interceptor;


import java.io.IOException;

import java.nio.channels.ClosedChannelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.isigntech.util.EdgeThreadLocal;

@Component
public class JwtRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(JwtRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String jwtToken = EdgeThreadLocal.edgeThreadLocalholder.get().get("jwt-token");
			if (jwtToken != null) {
				request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
			}
		}
		return execution.execute(request, body);
		}finally {
//			EdgeThreadLocal.edgeThreadLocalholder.get().clear();
		}
	}
}