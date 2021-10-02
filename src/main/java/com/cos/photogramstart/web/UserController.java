package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, Model model) {
		User userEntity = userService.profile(id);
		model.addAttribute("user", userEntity);
		return "user/profile";
	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id) {
		return "user/update";
	}
}
