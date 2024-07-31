package com.isigntech.edgeservice.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
@Component
public class AuthoritiesService {
	
	public Set<String> setAuthorities(Set<String> authorities) {
		Set<String> setAuthorities=new HashSet<String>();
		if(authorities!=null) {
			setAuthorities=authorities.stream()
				.map(role -> role.startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase())
				.collect(Collectors.toSet());
		}else {
			setAuthorities.add("ROLE_"+"USER");
		}
		return setAuthorities;
	}
}
