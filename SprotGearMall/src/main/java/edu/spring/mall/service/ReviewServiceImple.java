package edu.spring.mall.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewProductJoinVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.persistence.ReviewDAO;
@Service
public class ReviewServiceImple implements ReviewService {
	private Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	
	@Autowired
	private ReviewDAO dao;
	
	@Autowired
	private ProductService productService;

	@Override
	public int create(ReviewVO vo) throws Exception {
		logger.info("create ȣ��");
		int result = dao.insert(vo);
		return result;
	}

	@Override
	public List<ReviewVO> read(Principal principal) throws Exception {
		logger.info("read ȣ��");
		String memberId = "";
		if(principal != null) {
			memberId = principal.getName();
		}
		List<ReviewVO> list = dao.select(memberId);
		return list;
	}

	@Override
	public int update(ReviewVO vo) throws Exception {
		logger.info("update ȣ��");
		int result = dao.update(vo);
		return result;
	}

	@Override
	public int delete(int reviewId) throws Exception {
		logger.info("delete ȣ��");
		int result = dao.delete(reviewId);
		return result;
	}

	@Override
	public List<ReviewProductJoinVO> read(String memberId) throws Exception {
		logger.info("readȣ��");
		List<ReviewVO> list = dao.select(memberId);
		 List<ReviewProductJoinVO> reviewProduct = new ArrayList<>();
		 for(ReviewVO review : list) {
			 ProductVO vo = productService.read(review.getProductId());
			 ReviewProductJoinVO join = new ReviewProductJoinVO(vo, review);
			 reviewProduct.add(join);
		 }
	
		return reviewProduct;
	}

}
