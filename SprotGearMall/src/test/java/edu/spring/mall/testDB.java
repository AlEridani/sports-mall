package edu.spring.mall;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

public class testDB {
	private static final Logger logger = LoggerFactory.getLogger(testDB.class);
	
	@Autowired
	private static MemberDAO dao;
	
	@Test
	public void daoTest() {
		
		insert();
	}

	private void insert() {
		logger.info("insertȣ��");
		MemberVO vo = new MemberVO("test", "1234","ȫ�浿", "010-1234-5678", "test@test.com","����Ư���� ���ϱ� ���Ϸ�21��", "������");
		dao.insert(vo);
	}

}
