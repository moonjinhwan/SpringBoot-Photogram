package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@PostMapping("/auth/signup")
	public String 회원가입(SignupDto singDto) {
		User user = singDto.toEntity();
		User userEntity = authService.회원가입(user);
		//System.out.println(userEntity.toString());
		return "/auth/signin";
	}
}
