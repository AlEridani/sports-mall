package edu.spring.mall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.mall.domain.NotificationVO;
import edu.spring.mall.domain.ProductQnaJoinReplyVO;
import edu.spring.mall.domain.ProductQnaVO;
import edu.spring.mall.pageutil.PageCriteria;
import edu.spring.mall.persistence.ProductQnaDAO;

@Service
public class ProductQnaServiceImple implements ProductQnaService {
	private final Logger logger = LoggerFactory.getLogger(ProductQnaServiceImple.class);

	@Autowired
	private ProductQnaDAO dao;

	@Autowired
	private NotificationService notificationService;

	@Transactional
	@Override
	public int create(ProductQnaVO vo) throws Exception {
		int result = 1;
		logger.info("create 호출");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String memberId = authentication.getName();
		vo.setMemberId(memberId);
		vo.setPrdQnaContent(vo.getPrdQnaContent().replace("\n", "<br>"));
		dao.testInsert(vo);
		logger.info("돌려받은 prdQnaId : " + vo.getPrdQnaId());
		if (vo.getPrdQnaId() == 0) {
			result = 0;
			return result;
		}
		// 이제 여기서 알림 메서드 실행할거임
		// content에 넣을거
		// 내가 넣어야하는것 group type(상품문의) content() entityId(prdQnaId) url
		String notificationType = "상품 문의";
		// 상품번호 : 34 -새로운 상품 문의가 등록되어 답변이 필요합니다
		int entityId = vo.getProductId();
		String groupId = "ROLE_ADMIN";
		String notificationContent = "상품번호 : " + entityId + " - 새로운 " + notificationType + "가 등록되어 답변이 필요합니다";
		String targetUrl = "/mall/product/detail?productId=" + entityId;
		NotificationVO notification = new NotificationVO(0, null, groupId, notificationType, notificationContent,
				targetUrl, null, null, entityId);
		logger.info("notification insert 전 : " + notification.toString());
		notificationService.create(notification);
		logger.info("notification insert 이후");
		return result;
	}

	// 전체 문의 출력
	@Override
	public List<ProductQnaVO> read() {
		logger.info("read 호출 전체출력");
		return dao.select();
	}

	/**
	 * 디테일 접근
	 */
	@Override
	public List<ProductQnaJoinReplyVO> read(int productId, PageCriteria criteria) {
		logger.info("read(productId) 호출");
		List<ProductQnaJoinReplyVO> list = dao.select(productId, criteria);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String memberId = auth.getName();
		boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

		for (ProductQnaJoinReplyVO qna : list) {
			qna.getQna().setAdmin(isAdmin);
			;
			qna.getQna().setAuthor(memberId.equals(qna.getQna().getMemberId()));
			if (!qna.getQna().isAuthor() && !isAdmin) {
				String maskedMemberId = qna.getQna().getMemberId().substring(0, 3) + "***";
				qna.getQna().setMemberId(maskedMemberId);
			}
		}
		return list;
	}

	// 유저 개인 문의
	@Override
	public List<ProductQnaVO> read(String memberId) {
		logger.info("read(유저 개인문의) 호출");
		return dao.select(memberId);
	}

	/**
	 * 업데이트 밸류 호출
	 */
	@Override
	public ProductQnaVO readDetail(int prdQnaId) {
		logger.info("readDetail 호출");
		return dao.selectDetail(prdQnaId);
	}

	@Override
	public int update(ProductQnaVO vo) {
		logger.info("update 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int prdQnaId) {
		logger.info("delete 호출");
		return dao.delete(prdQnaId);
	}

	@Override
	public int getTotalCounts(int productId) {
		logger.info("getTotalCounts 호출");
		return dao.getTotalCount(productId);
	}

	@Override
	public int count(int prdQnaId) {
		logger.info("count호출");
		return dao.count(prdQnaId);
	}

}
