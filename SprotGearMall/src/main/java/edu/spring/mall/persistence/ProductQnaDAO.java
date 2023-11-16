package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductQnaDAO {
	int insert(ProductQnaVO vo);
	List<ProductQnaVO> select();
	List<ProductQnaVO> select(int productId, PageCriteria criteria);
	List<ProductQnaVO> select(String memberId);
	ProductQnaVO selectDetail(int prdQnaId);
	int update(ProductQnaVO vo);
	int delete(int prdQnaId);
	

}
