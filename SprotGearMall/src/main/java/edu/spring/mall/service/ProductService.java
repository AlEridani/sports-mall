package edu.spring.mall.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.spring.mall.domain.CartProductJoinVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo, MultipartFile file) throws IOException;
	List<ProductVO> read(PageCriteria criteria);
	ProductVO read(int productId);
	Map<String, Object> readProductById(int productId);
	int update(ProductVO vo);
	int delete(String productName);
	int getTotalCounts();
}