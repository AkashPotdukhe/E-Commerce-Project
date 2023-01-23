package com.csi.services;

import java.util.List;

import com.csi.utils.entities.Order;
import com.csi.utils.entities.OrderDetails;

public interface OrderdetailService {

	void saveOrderDetails(OrderDetails od);
	OrderDetails findById(int id);
	List<OrderDetails> findByOrder(Order order);
}
