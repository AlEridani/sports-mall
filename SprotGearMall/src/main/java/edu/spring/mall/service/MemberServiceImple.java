package edu.spring.mall.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MemberDAO dao;

	@Autowired
	private UserDetailsService service;

	@Override
	public int create(MemberVO vo) throws Exception {
		logger.info("create ȣ�� vo = " + vo.toString());
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		logger.info("��й�ȣ ��ȣȭ ����");
		int result = dao.insert(vo);
		return result;
	}

	@Override
	public MemberVO read(String memberId, String password) throws Exception {
		logger.info("read ȣ��");
		logger.info("memberId : " + memberId);

		return null;
	}

	@Override
	public String read(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Map userDetail) throws Exception {
		int result = 0;
		if (userDetail.containsKey("password")) {
			result = dao.updateUserPassword(userDetail);
			if (result == 1) {
				String memberId = (String) userDetail.get("memberId");
				UserDetails user = service.loadUserByUsername(memberId);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
						user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(newAuth);
			}
		} else {
			logger.info("user���� ����");
			result = dao.updateUserDetail(userDetail);
			if (result == 1) {
				String memberId = (String) userDetail.get("memberId");
				UserDetails user = service.loadUserByUsername(memberId);
				Authentication newAuth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
						user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(newAuth);

			}

		}
		return result; // �����δ� ������ ���� ������ �ٸ� ������ ���� ��ȯ�ϸ� �˴ϴ�.
	}

	@Override
	public int delete(String MemberId) throws Exception {
		logger.info("deleteȣ��");
		int result = dao.delete(MemberId);
		
		return result;
	}

}
