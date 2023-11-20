package edu.spring.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.pageutil.PageMaker;
import edu.spring.mall.service.ProductQnaService;

@RestController
@RequestMapping
public class ProductQnaRestController {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaRestController.class);
	
	@Autowired
	private ProductQnaService service;
	
	//받아야하는거 productId 페이지 
	@GetMapping(value="/product/prdQnaPaging")
	public ResponseEntity<Map<String, Object>> prdQnaGet(int productId, int page){
		
        PageCriteria criteria = new PageCriteria();
        criteria.setPage(page);
        List<ProductQnaVO> qnaList = service.read(productId, criteria);
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(service.getTotalCounts(productId));
        pageMaker.setPageData();
        boolean isAdmin = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
		    if ("ROLE_ADMIN".equals(authority.getAuthority())) {
		        isAdmin = true;
		        break;
		    }
		}
        String memberId = auth.getName();
    	for(ProductQnaVO x : qnaList) {
			if(!x.getMemberId().equals(memberId)) {
				x.setMemberId(x.getMemberId().substring(0,3)+ "***");
			}
		
		}
        Map<String, Object> response = new HashMap<>();
        response.put("qnaList", qnaList);
        response.put("pageMaker", pageMaker);
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/product/prdQna", produces = "application/json")
	public ResponseEntity<String> prdQnaPOST(@ModelAttribute ProductQnaVO vo){
		logger.info("prdQnaPost호출");
		String result = "";
	    String success = "{\"result\":\"success\"}";

		if(vo == null) {
			logger.info("vo가 null임");
		    return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}

			int dbinsert = service.create(vo);
			if(dbinsert == 1) {
				result = success;
			}

	    return new ResponseEntity<String>(result,HttpStatus.OK);

	}
	
	@PutMapping(value = "/product/prdQnaUpdate", produces = "application/json")
	public ResponseEntity<String> prdQnaPut(@ModelAttribute ProductQnaVO vo){
		logger.info("prdQnaPost호출");
		String result = "";
	    String success = "{\"result\":\"success\"}";

		if(vo == null) {
			logger.info("vo가 null임");
		    return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}
			int dbinsert = service.update(vo);
			if(dbinsert == 1) {
				result = success;
			}
	    return new ResponseEntity<String>(result,HttpStatus.OK);

	}
	

	
	@DeleteMapping("/product/deleteQna")
	public ResponseEntity<Void> deleteQna(@RequestBody ProductQnaVO vo) {
		logger.info("myqndaDeletePOST() 호출 : " + vo.getPrdQnaId());
		int prdQnaId = vo.getPrdQnaId();
		int result = 0;
		result = service.delete(prdQnaId);
		if(result == 1) {
			logger.info("삭제성공");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			logger.info("삭제 실패");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}

}
