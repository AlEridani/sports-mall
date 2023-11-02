package edu.spring.mall.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.spring.mall.domain.OrdersVO;
import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.domain.ReviewVO;
import edu.spring.mall.persistence.OrdersDAO;
import edu.spring.mall.service.ProductService;
import edu.spring.mall.service.ReviewService;

@Controller
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	OrdersDAO orderDAO;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/member/review")
	public void reviewGET(Model model, int productId, int orderId) {
		logger.info("reviewGET ȣ��" + productId + " | " + orderId);
		ProductVO productvo = productService.read(productId);
		logger.info("productvo Ȯ�� " + productvo);
		OrdersVO ordervo = orderDAO.select(orderId);
		logger.info("orderȮ�� : " + productvo);
		model.addAttribute("productVO", productvo);
		model.addAttribute("orderVO", ordervo);
		
	}
	
//	@PostMapping("")
//	public void reviewPost(@RequestParam int reviewRating, @RequestParam String reviewContent,
//			@RequestParam int productId,Principal principal) throws Exception{
//		logger.info("reviewPOST ȣ��");
//		String memberId = "";
//		if(principal != null) {
//			memberId = principal.getName();
//		}
//		ReviewVO vo = new ReviewVO(0, productId, reviewContent, reviewRating, null, memberId);
//		reviewService.create(vo);
//		
//	}

	
	@GetMapping("member/reviewList")
	public void reviewGET(Model model, Principal principal) throws Exception {
		logger.info("���� ����Ʈ ���");
		if(principal != null) {
			List<ReviewVO> list = reviewService.read(principal);
			model.addAttribute("list", list);
			logger.info("������ ��ǰ ������");
		}else {
			logger.info("�α����� �ȵ�");
		}
	}
}
