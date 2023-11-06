package edu.spring.mall.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.persistence.MemberDAO;

@RestController
@RequestMapping
public class RestLoginController {
	private static final Logger logger = LoggerFactory.getLogger(RestLoginController.class);

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;



	@PostMapping("member/checkid")
    public ResponseEntity<Integer> checkId(@RequestBody Map<String, String> request) {
		logger.info("아이디 중복체크 메소드 호출");
		Integer result = dao.select(request.get("memberId"));
		logger.info("중복 아이디 갯수 : " + result);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	
//	@PostMapping("/member/userUpdate")
//	public ResponseEntity<String> userUpdate(@RequestBody
//			Map<String, String> request){
//		Map<String,String> user = 
//		
//		return new ResponseEntity<String>(user, Http);
//		
//	}
	
	@PostMapping("member/passwordCheck")
	public ResponseEntity<Integer> passwordCheck(@RequestBody Map<String,String> request){
		Integer result = 0;
		String memberId = request.get("memberId");
		String password = request.get("password");
		UserDetails user = service.loadUserByUsername(memberId);
	    String encodedPassword = user.getPassword();
	    

	    if (passwordEncoder.matches(password, encodedPassword)) {
	    	result = 1;
	    	return new ResponseEntity<Integer>(result, HttpStatus.OK);
	    }else {
	    	return new ResponseEntity<Integer>(result, HttpStatus.OK);
	    }
		
		
	}
	
	

}
