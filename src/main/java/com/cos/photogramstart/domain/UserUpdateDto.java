package com.cos.photogramstart.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank
	private String name; // 필수
	@NotBlank
	private String password; // 필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
