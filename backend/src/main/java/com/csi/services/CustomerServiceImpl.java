package com.csi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csi.daos.CustomerDao;
import com.csi.utils.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired private CustomerDao dao;

	@Override
	public void registerCustomer(Customer cust) {
		dao.save(cust);
	}

	@Override
	public List<Customer> allCustomers() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public Customer validate(String userid, String pwd) {
		Customer cc=dao.findByUserid(userid);
		if(cc!=null && cc.getPwd().equals(pwd)) {
			return cc;
		}
		return null;
	}
	
	@Override
	public boolean verifyUserId(String userid) {
		// TODO Auto-generated method stub
		return dao.findByUserid(userid)!=null;
	}

	@Override
	public void updateProfile(Customer cust) {
		// TODO Auto-generated method stub
		if(cust.getPwd().equals("") || cust.getPwd()==null) {
			cust.setPwd(findById(cust.getId()).getPwd());
		}
		dao.save(cust);	
	}

	@Override
	public List<Customer> findByName(String name) {
		return dao.findAll().stream().filter(c1->c1.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<Customer> sortByName() {
		return dao.findAll().stream().sorted((c1,c2)->c1.getName().compareToIgnoreCase(c2.getName())).collect(Collectors.toList());
	}


}
