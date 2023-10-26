package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.ProductDAO;


@Service
public class ProductServiceImple implements ProductService {
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductServiceImple.class);
	
	@Autowired
	private ProductDAO dao;

	@Override
	public int create(ProductVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString()); 
		return dao.insert(vo);
	}

	@Override
	public List<ProductVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public ProductVO read(String productName) {
		logger.info("read() ȣ�� : ProductName = " + productName);
		return dao.select(productName);
	}

	@Override
	public int update(ProductVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(String productName) {
		logger.info("delete() ȣ�� : productName = " + productName);
		return dao.delete(productName);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return dao.getTotalCounts();
	}

}