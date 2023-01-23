package com.csi.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.csi.models.OrderDetailsDTO;
import com.csi.models.OrderResponseDTO;
import com.csi.models.PlaceOrderDTO;
import com.csi.models.Response;
import com.csi.services.AddressService;
import com.csi.services.OrderService;
import com.csi.services.PaymentService;
import com.csi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csi.utils.entities.Address;
import com.csi.utils.entities.Customer;
import com.csi.utils.entities.Order;
import com.csi.utils.entities.OrderDetails;
import com.csi.utils.entities.Payment;
import com.csi.utils.entities.Product;
import com.csi.models.CartDTO;
import com.csi.services.CustomerService;
import com.csi.services.EmailService;
import com.csi.services.OrderdetailService;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	OrderService orderService;
	@Autowired
    CustomerService customerService;
	@Autowired
	AddressService addressService;
	@Autowired
	PaymentService paymentService;
	@Autowired
    OrderdetailService orderDetailsService;
	@Autowired
	ProductService productService;
	@Autowired
    EmailService email;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PlaceOrderDTO dto) {
		Address address=addressService.saveAddress(dto.getAddress());
		dto.getPayment().setPaymentdate(new Date());
		Payment payment=paymentService.savePayment(dto.getPayment());
		Order order=new Order();
		order.setOrderDate(new Date());
		order.setAddress(address);
		order.setPayment(payment);
		Customer customer=customerService.findById(dto.getCustomerid());
		order.setCustomer(customer);
		Order orders=orderService.saveOrder(order);
		String selleremail="";
		String sellername="";
		Product product=null;
		int qty=0;
		
		for(CartDTO cart : dto.getCart()) {
			OrderDetails od=new OrderDetails();
			od.setOrder(orders);
			od.setQty(cart.getQty());
			product=productService.findProductById(cart.getProdid());
			selleremail=product.getSeller().getUserid();
			sellername=product.getSeller().getName();
			qty=cart.getQty();
			od.setProduct(product);
			orderDetailsService.saveOrderDetails(od);
		}
		
		System.out.println(dto.getAddress());
		System.out.println(dto.getCustomerid());
		System.out.println(dto.getPayment());
		System.out.println(dto.getCart().get(0));
		//send email
		System.out.println(selleremail+" "+sellername+" "+qty);
		
		email.sendSimpleMessage(selleremail, "New Order Received", 
				"Dear "+sellername+",<br>You have been received new order from a customer"+
		"<br>Product Name "+product.getPname()+"<br>Product Quantity "+qty);
		
		return Response.status(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllOrders(Optional<Integer> custid) {
		List<Order> result=null;
		if(custid.isPresent()) {
			Customer customer=customerService.findById(custid.get());
			 result= orderService.getCustomerOrders(customer);
		}else {
			result = orderService.getAllOrders();
		}
		return Response.success(result);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOrderById(@PathVariable("id") int id) {
		Order order = orderService.findById(id);
		List<OrderDetails> details=orderDetailsService.findByOrder(order);
		List<OrderDetailsDTO> detailsdto=new ArrayList<OrderDetailsDTO>();
		details.forEach(od -> {
			OrderDetailsDTO dto=OrderDetailsDTO.fromEntity(od);
			detailsdto.add(dto);
		});
		OrderResponseDTO result=new OrderResponseDTO();
		result.setOrder(order);
		result.setDetails(detailsdto);
		return Response.success(result);
	}
}
