package com.isigntech.edgeservice.dto;

import java.util.Set;

import com.isigntech.edgeservice.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
	
	private String email;
	private String password;
}
