package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.LikesVO;

@Repository
public class LikesDAOImple implements LikesDAO {
	
	private final Logger logger = LoggerFactory.getLogger(LikesDAOImple.class);
	private static final String NAMESPACE ="edu.spring.mall.LikesMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(LikesVO vo) {
		logger.info("inser ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert" , vo);
	}

	@Override
	public int select(LikesVO vo) {
		logger.info("���ƿ� �������� Ȯ���ϴ°�");
		return sqlSession.selectOne(NAMESPACE + ".checkByLiked", vo);
	}
	
	@Override
	public List<Integer> selectUserLiked(String memberId) {
		logger.info("selectUserLiked ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select" , memberId);
	}

	@Override
	public int delete(LikesVO vo) {
		logger.info("delete ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", vo);
	}

	



	

}
