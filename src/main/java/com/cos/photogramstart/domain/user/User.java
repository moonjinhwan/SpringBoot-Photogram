package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;

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
	@Column(length = 20, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;
	private String website; // 웹 사이트
	private String bio; // 자기 소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;

	private String profileImageUrl;
	private String role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Image> images;

	private LocalDateTime createTime;

	@PrePersist
	public void createDate() {
		this.createTime = LocalDateTime.now();
	}

}
