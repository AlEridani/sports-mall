package edu.spring.mall;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.mall.domain.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class) 

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})  
@WebAppConfiguration 
public class SqlSessionTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(SqlSessionTest.class);
	
	private static final String NAMESPACE = 
			"edu.spring.mall.ProductMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
//	@Test
//	public void testInsert() {
//		ProductVO vo = new ProductVO(0,"test", 1, 1,"test","test","test",0);
//		int result = sqlSession.insert(NAMESPACE + ".insert",vo);
//		logger.info(result + "�� ����");
//	}
	
//	@Test
//	public void testSelectAll() {
//		// List<ProductVO> select()�޼ҵ带 �׽�Ʈ�ϴ� �޼ҵ��Դϴ�.
//		List<ProductVO> list = sqlSession.selectList(NAMESPACE + ".select_all");
//		logger.info(list.toString());
//	}
	
//	@Test
//	public void testSelectByProductName() {
//		// ProductVO select(String productName)�޼ҵ带 �׽�Ʈ�ϴ� �޼ҵ��Դϴ�.
//		String productName = "���ù����";
//		ProductVO vo = sqlSession.selectOne(NAMESPACE + ".select_by_product_name",productName);
//		logger.info(vo.toString());
//	}
	
	@Test
	public void testUpdate() {
		ProductVO vo = new ProductVO(4,"���ù����",80000,11,"����ζ�","�̹������","�����",1);
		vo = sqlSession.update(NAMESPACE)
		
		logger.info(vo.toString());
	}
	
}
