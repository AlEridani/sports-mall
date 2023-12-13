package edu.spring.mall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.mall.domain.QnaReplyVO;
import edu.spring.mall.controller.QnaReplyRESTController;
import edu.spring.mall.service.QnaReplyService;

@RestController
@RequestMapping(value = "/qnaBoard/replies")
public class QnaReplyRESTController {
	private static final Logger logger = LoggerFactory.getLogger(QnaReplyRESTController.class);

	@Autowired
	private QnaReplyService qnaReplyService;

	@PostMapping(produces = "application/json")
	public ResponseEntity<Integer> createReply(@RequestBody QnaReplyVO vo) {
		logger.info("createReply() 호출 : vo = " + vo.toString());
		logger.info("vo.getMemberId() = " + vo.getMemberId());
		logger.info("vo.getQnaReplyContent() = " + vo.getQnaReplyContent());
		// 입력값 null일때
		if(vo.getMemberId() == null || vo.getQnaReplyContent() == null ||
				vo.getMemberId().trim().isEmpty() || vo.getQnaReplyContent().trim().isEmpty()) {
			logger.info("댓글 null값");
			int result = 0;
			
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			
			int result = 0;
			try {
				result = qnaReplyService.create(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
	}

	@GetMapping("/all/{qnaBoardId}")
	public ResponseEntity<List<QnaReplyVO>> readReplies(@PathVariable("qnaBoardId") int qnaBoardId) {
		logger.info("readReplies() 호출 : qnaBoardId = " + qnaBoardId);

		List<QnaReplyVO> list = qnaReplyService.read(qnaBoardId);
		return new ResponseEntity<List<QnaReplyVO>>(list, HttpStatus.OK);

	}

	@PutMapping(value = "/{qnaReplyId}", produces = "application/json")
	public ResponseEntity<Integer> updateReply(@PathVariable("qnaReplyId") int qnaReplyId,
			@RequestBody String qnaReplyContent) {
		// 수정내용이 null일때
		if(qnaReplyContent == null || qnaReplyContent.trim().isEmpty()) {
			
			int result = 0;
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
			
		}else {
			
		int result = qnaReplyService.update(qnaReplyId, qnaReplyContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
		}
		
	}

	@DeleteMapping(value = "/{qnaReplyId}", produces = "application/json")
	public ResponseEntity<Integer> deleteReply(@PathVariable("qnaReplyId") int qnaReplyId) {
		logger.info("qnaReplyId = " + qnaReplyId);

		int result = 0;
		try {
			result = qnaReplyService.delete(qnaReplyId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}