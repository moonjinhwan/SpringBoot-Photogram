package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Transactional
	public User update(int id, User user) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{return new CustomValidationException("찾을 수 없는 id 입니다");});
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setPassword(encPassword);
		userEntity.setName(user.getName());
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	}
	
	@Transactional(readOnly = true)
	public User profile(int id) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new CustomException("해당 프로필 페이지는 없는 페이지 입니다.");
		});
		
		return userEntity;
	}
}