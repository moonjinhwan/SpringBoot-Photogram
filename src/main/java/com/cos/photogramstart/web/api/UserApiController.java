package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.User;
import com.cos.photogramstart.domain.UserUpdateDto;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.auth.CMRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		} else {
			User user = userUpdateDto.toEntity();
			User userEntity = userService.update(id, user);
			principalDetails.setUser(userEntity);
			return new CMRespDto<>(1, "수정완료", userEntity);
		}
	}
}
