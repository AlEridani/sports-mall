package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.OrdersVO;

@Repository
public class OrdersDAOImple implements OrdersDAO {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(OrdersDAOImple.class);
	
	private static final String NAMESPACE = 
			"edu.spring.mall.OrdersMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(OrdersVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<OrdersVO> select(String memberId) {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override
	public int delete(String memberId, int productId) {
		logger.info("delete() ȣ��");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("productId", productId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}

}
