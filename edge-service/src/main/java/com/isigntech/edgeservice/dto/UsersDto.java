package com.isigntech.edgeservice.dto;

import java.util.Set;

import com.isigntech.edgeservice.entity.Users;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must be in the format 'something@gmail.com'")
	@Size(min = 11, max = 30, message = "Email must be between 11 and 30 characters")
    @NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "First name is mandatory")
    @Size(min = 1, max = 25, message = "First name must be between 1 and 25 characters")
	private String firstName;
	@NotBlank(message = "Last name is mandatory")
    @Size(min = 1, max = 25, message = "Last name must be between 1 and 25 characters")
	private String lastName;
	@NotBlank(message = "Password is mandatory")
	@Pattern(
	    regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/]).*$",
 
	    message = "Password must contain at least one special character"
	)
	private String password;
	private Boolean enabled=true;
	private Set<String> authorities;
	
	public UsersDto(Users users) {
		this.email=users.getEmail();
		this.firstName=users.getFirstName();
		this.lastName=users.getLastName();
		this.password=users.getPassword();
		this.authorities=users.getAuthorities();
		this.enabled=enabled;
	}
}
