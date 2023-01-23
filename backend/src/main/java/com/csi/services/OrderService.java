package com.csi.services;

import java.util.List;

import com.csi.utils.entities.Customer;
import com.csi.utils.entities.Order;

public interface OrderService {

	Order saveOrder(Order order);
	List<Order> getAllOrders();
	List<Order> getCustomerOrders(Customer customer);
	Order findById(int id);
}
