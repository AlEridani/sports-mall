package edu.spring.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.MemberVO;
import edu.spring.mall.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })

@WebAppConfiguration
public class testDB {
	private static final Logger logger = LoggerFactory.getLogger(testDB.class);

	@Autowired
	private MemberDAO dao;

	@Test
	public void daoTest() {

		insert();
	}

	private void insert() {
		MemberVO vo = new MemberVO("test", "1234", "ȫ�浿", "010-1234-5678", "test@test.com", "����Ư���� ���ϱ� ���Ϸ�21��", "������");
		logger.info("insertȣ��");
		
			dao.insert(vo);
		

		logger.info("insertȣ�� ��");
	}

}
