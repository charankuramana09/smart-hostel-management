package com.admin.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "com")
@Configuration
public class UserDetailsConfiguration {

    @Bean
   public  ModelMapper mapper() {
		return new ModelMapper();
	}

    
    @Bean
    public RestTemplate restTemple() {
    	
    	return new RestTemplate();
    }
    
}
