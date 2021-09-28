package com.cos.photogramstart.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	
	private String bio;
	private String name;
	private String email;
	private String website;
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	private LocalDateTime createTime;
	
	@PrePersist
	public void createDate() {
		this.createTime = LocalDateTime.now(); 
	}
}
