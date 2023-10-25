package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.mall.domain.ProductVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.service.ProductService;

@RequestMapping(value="/product")
public class ProductController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductController.class);
	
	private ProductService productService;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() ȣ��");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<ProductVO> list = productService.read(criteria);
		model.addAttribute("list",list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(productService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker",pageMaker);
		
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(ProductVO vo, RedirectAttributes reAttr) {
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = productService.create(vo);
		logger.info(result + "�� ����");
		if(result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/product/list";
		} else {
			return "redirect:/product/register";
		}
	} // end registerPOST()
	
	@GetMapping("/detail")
	public void detail(Model model, String productName, Integer page ) {
		logger.info("detail() ȣ�� : ProductName = " + productName);
		ProductVO vo = productService.read(productName);
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
	} // end detail()
	
	@GetMapping("/update")
	public void updateGET(Model model, String productName, Integer page) {
		logger.info("updateGET() ȣ�� : productName = " + productName);
		ProductVO vo = productService.read(productName);
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
	} // end updateGET()
	
	
	
	
} // end ProductController
