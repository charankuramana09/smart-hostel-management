package com.isigntech.filter;

import java.io.IOException;




import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.isigntech.service.JwtService;
import com.isigntech.util.EdgeThreadLocal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Lazy
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		final String authorizationHeader = request.getHeader("Authorization");

		String email = null;
		String jwt = null;
		try {
			if (authorizationHeader != null) {
				if (authorizationHeader.startsWith("Bearer "))
					jwt = authorizationHeader.substring(7);
				email = jwtService.extractEmailname(jwt);
			}
			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				if (jwtService.validateToken(jwt, email)) {
					Collection<? extends GrantedAuthority> convertToGrantedAuthorities = jwtService
							.getAuthoritiesFromToken(jwt);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							email, null, convertToGrantedAuthorities);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					EdgeThreadLocal.edgeThreadLocalholder.get().put("jwt-token", jwt);
				} else {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Invalid token. Please login again.");
					return;
				}
			}
		} catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException  e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println(e);
			response.getWriter().write("Login expired or wrong token. Please login again.");
			return;
		}
		filterChain.doFilter(request, response);
	}

}
