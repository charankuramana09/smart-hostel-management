package com.isigntech.edgeservice.controller;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isigntech.edgeservice.dto.Login;
import com.isigntech.edgeservice.dto.UsersDto;
import com.isigntech.edgeservice.entity.Users;
import com.isigntech.edgeservice.service.AuthoritiesService;
import com.isigntech.edgeservice.service.JwtService;
import com.isigntech.edgeservice.service.UserDetailsServiceImpl;
import com.isigntech.edgeservice.validator.PasswordValidator;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="/*")
public class AuthenthicationController {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;


	@PostMapping("/register")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR','ROLE_USER')")
	public ResponseEntity<Map<String, String>> addUser(@Valid @RequestBody UsersDto usersDto ) {
		return ResponseEntity.ok(userDetailsServiceImpl.addUser(usersDto));
	}

	@PostMapping("/login")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR','ROLE_USER')")
	public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Login loginUser) {
		return ResponseEntity.ok(userDetailsServiceImpl.getLoginDetails(loginUser));
	}
	@PostMapping("/updateActive")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	public ResponseEntity<Map<String, String>> deleteByEmail(@RequestParam("email") String email){
		return ResponseEntity.ok(userDetailsServiceImpl.updateActive(email));
	}
	
}
