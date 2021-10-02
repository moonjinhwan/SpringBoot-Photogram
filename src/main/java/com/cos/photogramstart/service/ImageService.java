package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	public void 이미지저장(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String fileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();//파일명 만들기
		//외부파일 경로 만들기
		Path imageFilePath = Paths.get(uploadFolder+fileName);
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image image = imageUploadDto.toEntity(fileName, principalDetails.getUser());
		imageRepository.save(image);
	}
	 
}
