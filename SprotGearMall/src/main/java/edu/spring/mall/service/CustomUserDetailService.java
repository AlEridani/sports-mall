package edu.spring.mall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.spring.mall.persistence.MemberDAO;
import edu.spring.mall.security.CustomUserDetails;



public class CustomUserDetailService implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
	@Autowired
	private MemberDAO dao;
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
			logger.info("loadUserByUsername ȣ�� username  : " + username);
			CustomUserDetails user = dao.login(username);
			if(user == null) {
				logger.info("user == null�϶� ȣ���");
		    throw new UsernameNotFoundException("���̵� ã�� �� ���� " + username);
		    }
			
		    return user;
	}

	
	

}
