package edu.spring.mall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.domain.ProductVO;
@Repository
public class ProductQnaDAOImple implements ProductQnaDAO {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaDAOImple.class);
	private final String NAMESPACE = "edu.spring.mall.ProductQnaMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductQnaVO vo) {
		logger.info("insert");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String memberId = authentication.getName();
		vo.setMemberId(memberId);
		return sqlSession.insert(NAMESPACE + ".insert",vo);
	}
	//관리자페이지에 보여줄거
	@Override
	public List<ProductQnaVO> select() {
		return null;
	}
	//디테일창에 보여줄거
	@Override
	public List<ProductQnaVO> select(int productId) {
		logger.info("select 호출(디테일)");
		
		return sqlSession.selectList(NAMESPACE + ".selectDetail", productId);
	}

	@Override
	public ProductVO selectDetail(int prdQnaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(int prdQnaId, ProductQnaVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int prdQnaId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
