package edu.spring.mall.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.service.ReviewService;

@RestController
@RequestMapping
public class RestReviewController {
	private final Logger logger = LoggerFactory.getLogger(RestReviewController.class);
	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/member/review")
	public ResponseEntity<String> reviewPost(@RequestBody ReviewVO vo,Principal principal) throws Exception{
		logger.info("reviewPOST ȣ��");
		String result = "";
		String memberId = "";
		int dbResult = 0;
		if(principal != null) {
			memberId = principal.getName();
			vo.setMemberId(memberId);
		}else {
			logger.info("�α��� ���� ã�� �� ����");
			return new ResponseEntity<String>(result, HttpStatus.FORBIDDEN);
		}
		dbResult = reviewService.create(vo);
		if(dbResult == 1) {
			logger.info("���� ��� ����");
			result = "success";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}
}
