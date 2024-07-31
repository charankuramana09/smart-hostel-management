package com.isigntech.edgeservice.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isigntech.edgeservice.dto.Login;
import com.isigntech.edgeservice.dto.UsersDto;
import com.isigntech.edgeservice.entity.Users;
import com.isigntech.edgeservice.exception.InactiveUserException;
import com.isigntech.edgeservice.exception.UserAlreadyInDatabaseException;
import com.isigntech.edgeservice.exception.UserNotFoundDbException;
import com.isigntech.edgeservice.repository.UsersRepository;
import com.isigntech.edgeservice.util.ThreadLocalUserContext;
import com.isigntech.edgeservice.validator.PasswordValidator;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuthoritiesService authoritiesService;
	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundDbException("User does not exist"));
		this.checkActiveUser(user.getEnabled());
		ThreadLocalUserContext.setUser(user);
		String dbEmail = user.getEmail();
		String dbPassword = user.getPassword();
		Set<String> authorities = user.getAuthorities();

		Set<GrantedAuthority> grantedAuthorities = authorities.stream()
				.map(role -> new SimpleGrantedAuthority(
						role.startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase()))
				.collect(Collectors.toSet());

		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				dbEmail, dbPassword, grantedAuthorities);
		return userDetails;

	}

	public Map<String, String> addUser(UsersDto usersDto) {
		Users user = new Users(usersDto);
		this.validateUser(usersDto.getEmail());
		passwordValidator.passwordSizeChecker(user.getPassword());
		user.getAuthorities();
		String password = user.getPassword();
		String encodePassword = bCryptPasswordEncoder.encode(password);
		Set<String> authorities = authoritiesService.setAuthorities(user.getAuthorities());
		Users encodeUser = new Users(user.getEmail(), user.getFirstName(), user.getLastName(), encodePassword,
				user.getEnabled(), authorities);
		userRepository.save(encodeUser);
		Map<String, String> response = new HashMap<>();
		response.put("message", "success");
		return response;

	}

	public Map<String, String> getLoginDetails(Login loginUser) {
		passwordValidator.passwordSizeChecker(loginUser.getPassword());
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
		if (authenticate.isAuthenticated()) {
			String token = jwtService.generateToken(userDetails);
			System.err.println(token);
			Map<String, String> userMap = ThreadLocalUserContext.getUser();
			userMap.put("jwtToken", token);
			return userMap;
		} else {
			throw new UsernameNotFoundException("User Name Not Found");
		}
	}

	public void validateUser(String email) {
		Optional<Users> user = userRepository.findByEmail(email);
		if (!user.isEmpty()) {
			throw new UserAlreadyInDatabaseException("UserAlready in Database ");
		}
	}
	public void checkActiveUser(Boolean activeUser) {
		if(!activeUser) {
			throw new InactiveUserException("User is Inactive");
		}
	}
	public Map<String,String> updateActive(String email) {
		try {
		userRepository.deleteByEmail(email);
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "success");
		return map;
		}catch(UserNotFoundDbException e){
			throw new UserNotFoundDbException("User Not Found");
		}
	}
}
