package edu.spring.mall.persistence;

import java.util.List;

import edu.spring.mall.domain.OrdersVO;

public interface OrdersDAO {
	int insert(OrdersVO vo);
	List<OrdersVO> select(String memberId);
	List<OrdersVO> selectRefund(String orderState);
	OrdersVO select(int orderId);
	int update(OrdersVO vo);
	int delete(String memberId, int productId);
	int delete(int orderId);
    boolean hasReview(int orderId);

}
