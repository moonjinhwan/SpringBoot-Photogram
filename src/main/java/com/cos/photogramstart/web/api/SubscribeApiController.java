package com.cos.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.auth.CMRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {
	
	private final SubscribeService subscribeService;
	
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails , @PathVariable int toUserId) {
		int fromUserId = principalDetails.getUser().getId();
		subscribeService.구독하기(fromUserId, toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1, "구독성공", null), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subcribe/{toUserId")
	public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails , @PathVariable int toUserId) {
		int fromUserId = principalDetails.getUser().getId();
		subscribeService.구독취소(fromUserId, toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1, "구독취소 성공", null), HttpStatus.OK);
	}
}
