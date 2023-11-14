package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

@Repository
public class ProductDAOImple implements ProductDAO{
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.ProductMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ProductVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}


	//리스트 불러오기
	@Override
	public List<ProductVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}


	//검색용
	@Override
	public ProductVO selectByName(String productName) {
		logger.info("select(productName) 호출 : productName = " + productName);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_name",productName);
	}


	//디테일용
	@Override
	public ProductVO selectById(int productId) {
		logger.info("select(productId) 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", productId);
	}
	@Override
	public List<ProductVO> select(PageCriteria criteria) {
		logger.info("select(criteria) 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging" , criteria);
	}

	@Override
	public int update(ProductVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString() );
		return sqlSession.update(NAMESPACE + ".update",vo);
	}

	@Override
	public int delete(String productName) {
		logger.info("delete() 호출 : productId = " + productName);
		return sqlSession.delete(NAMESPACE + ".delete",productName);
	}

	

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCount()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<ProductVO> selectPaging(String productName) {
		logger.info("selectPaging() 호출 : product = " + productName);
		return sqlSession.selectList(NAMESPACE + ".select_by_productName","%" + productName + "%");
	}


	@Override
	public List<ProductVO> select(String searchText, PageCriteria criteria) {
		logger.info("select() 호출 : " + searchText);
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("searchText", searchText);
	    paramMap.put("criteria", criteria);
	    return sqlSession.selectList(NAMESPACE + ".select_by_search_text", paramMap);
	    
	    
	    
	}





	
	

}