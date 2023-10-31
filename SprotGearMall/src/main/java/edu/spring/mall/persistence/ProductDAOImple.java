package edu.spring.mall.persistence;

import java.util.List;

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
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	//��� ȣ��
	@Override
	public List<ProductVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

	//�˻�
	@Override
	public ProductVO selectByName(String productName) {
		logger.info("select(productName) ȣ�� : productName = " + productName);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_name",productName);
	}
	//���������ٿ�
	@Override
	public ProductVO selectById(int productId) {
		logger.info("select(productId) ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", productId);
	}
	//���ƿ��
	@Override
	public ProductVO selectByIds(int productId) {
		logger.info("select(productId) ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".select_products_by_ids", productId);
	}
	
	@Override
	public List<ProductVO> select(PageCriteria criteria) {
		logger.info("select(criteria) ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging" , criteria);
	}

	@Override
	public int update(ProductVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString() );
		return sqlSession.update(NAMESPACE + ".update",vo);
	}

	@Override
	public int delete(String productName) {
		logger.info("delete() ȣ�� : productId = " + productName);
		return sqlSession.delete(NAMESPACE + ".delete",productName);
	}

	

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCount()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<ProductVO> selectPaging(String productName) {
		logger.info("selectPaging() ȣ�� : product = " + productName);
		return sqlSession.selectList(NAMESPACE + ".select_by_productName","%" + productName + "%");
	}





	
	

}