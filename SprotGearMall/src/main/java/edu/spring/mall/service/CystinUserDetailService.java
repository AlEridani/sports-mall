package edu.spring.mall.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CystinUserDetailService implements UserDetailsService {

	  private Map<String, UserDetails> users = new HashMap<>();

	    public CystinUserDetailService() {
	        // XML���� ������ ����� ������ �����ͼ� UserDetails ��ü�� �����Ͽ� �����մϴ�.
	        UserDetails adminUser = User.builder()
	                .username("admin")
	                .password("{noop}1234")
	                .roles("ADMIN")
	                .build();
	        UserDetails regularUser = User.builder()
	                .username("user")
	                .password("{noop}1234")
	                .roles("USER")
	                .build();

	        users.put(adminUser.getUsername(), adminUser);
	        users.put(regularUser.getUsername(), regularUser);
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        UserDetails user = users.get(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	        return user;
	    }

}
