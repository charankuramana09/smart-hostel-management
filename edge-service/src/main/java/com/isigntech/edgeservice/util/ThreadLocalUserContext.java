package com.isigntech.edgeservice.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import com.isigntech.edgeservice.entity.Users;

public class ThreadLocalUserContext {
    private static final ThreadLocal<Users> userContext = new ThreadLocal<>();

    public static void setUser(Users user) {
        userContext.set(user);
    }

    public static Map<String,String> getUser() {
    	return setUserNonSensitiveinfo(userContext.get());
    }

    public static void clear() {
        userContext.remove();
    }
    
    public static Map<String,String> setUserNonSensitiveinfo(Users users) {
    	 String authoritiesString = users.getAuthorities().stream()
                 .collect(Collectors.joining(","));
    	 
    	Map<String,String> userMap=new HashMap();
    	userMap.put("authorities",authoritiesString);
    	userMap.put("firstName", users.getFirstName());
    	userMap.put("lastName", users.getLastName());	
    	userMap.put("email", users.getEmail());
    	return userMap;
    	
    }
}
