package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.User;
import com.cos.photogramstart.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public User 회원가입(User user) {
		String encode = passwordEncoder.encode(user.getPassword());
		user.setPassword(encode);
		user.setRole("ROLE_USER");
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
