package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@RestController
public class ImageRestController {
	private final Logger logger = LoggerFactory.getLogger(ImageRestController.class);
	
	@Autowired
	private Storage storage;
	
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
	    String bucketName = "edu-mall-img"; // 버킷 이름 설정
	    BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
	    try {
	        storage.create(blobInfo, file.getBytes());
	        return "File uploaded successfully.";
	    } catch (Exception e) {
	        return "The file upload failed: " + e.getMessage();
	    }
	}
}
