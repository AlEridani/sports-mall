package edu.spring.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.LikesVO;

@RestController
@RequestMapping
public class RestLikeController {
	private final Logger logger = LoggerFactory.getLogger(RestLikeController.class);
	

	
	
	@PostMapping("/like")
	public ResponseEntity<String> InsertLike(@RequestBody LikesVO vo){
		logger.info("���ƿ� insert");
		String result = "";
		
		//ó�� ������ ������ ���ƿ����� �ƴ��� �Ǵ��ؾ���
		//��ư�� ������ memberId productId�� �����ð���

		
		logger.info("���ƿ� ��� ����");
		
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
