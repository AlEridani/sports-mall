package edu.spring.mall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.spring.mall.domain.MemberVO;


public interface MemberService {
	int create(MemberVO vo) throws Exception;
	MemberVO read(String memberId, String password)throws Exception;
	MemberVO readDetail(String memberId);
	List<MemberVO> read();
	int checkDuplication(String memberId);
	List<MemberVO>read(String memberId);
	int update(Map userDetail)throws Exception;
	int delete(String MemberId) throws Exception;

}
