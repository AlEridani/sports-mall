package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.mall.domain.ReplyReplyVO;
import edu.spring.mall.persistence.ReplyReplyDAO;

@Service
public class ReplyReplyServiceImple implements ReplyReplyService {
	private static final Logger logger = 
			LoggerFactory.getLogger(QnaReplyServiceImple.class);
	
	@Autowired
	private ReplyReplyDAO replyReplyDAO;

	@Override
	public int create(ReplyReplyVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = replyReplyDAO.insert(vo);
		logger.info(resultInsert + " �� ��� �Է� ����");
		return 1;
	}

	@Override
	public List<ReplyReplyVO> read(int qnaReplyId) {
		logger.info("read() ȣ�� : qnaReplyId = " + qnaReplyId);
		return replyReplyDAO.select(qnaReplyId);
	}

	@Override
	public int update(int replyReplyId, String replyReplyContent) {
		logger.info("update() ȣ��");
		logger.info("replyReplyId = " + replyReplyId + ", replyReplyContent = " + replyReplyContent);
		return replyReplyDAO.update(replyReplyId, replyReplyContent);
	}

	@Override
	public int delete(int replyReplyId) throws Exception {
		logger.info("delete() ȣ�� : replyReplyId = " + replyReplyId);
		return replyReplyDAO.delete(replyReplyId);
	}

}
