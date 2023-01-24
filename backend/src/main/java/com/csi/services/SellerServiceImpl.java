package com.csi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csi.daos.SellerDao;
import com.csi.utils.entities.Seller;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired private SellerDao dao;



	@Override
	public void registerSeller(Seller seller) {
		// TODO Auto-generated method stub
		dao.save(seller);
	}

	@Override
	public List<Seller> allSellers() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Seller findById(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public Seller validate(String userid, String pwd) {
		Seller seller=dao.findByUserid(userid);
		if(seller!=null && seller.getPwd().equals(pwd)) {
			return seller;
		}
		return null;
	}

	@Override
	public void deleteSeller(int id) {
		// TODO Auto-generated method stub
		Seller seller=dao.getById(id);
		dao.delete(seller);
	}

	@Override
	public List<Seller> findSellersByName(String name) {
		return dao.findAll().stream().filter(s->s.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<Seller> sortSellerByName() {
		return dao.findAll().stream().sorted((s1,s2)->s1.getName().compareToIgnoreCase(s2.getName())).collect(Collectors.toList());
	}

}
