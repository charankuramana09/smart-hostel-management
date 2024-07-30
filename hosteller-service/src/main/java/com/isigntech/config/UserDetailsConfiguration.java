package com.isigntech.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com")
@Configuration
public class UserDetailsConfiguration {

    @Bean
   public  ModelMapper mapper() {
		return new ModelMapper();
	}

}
