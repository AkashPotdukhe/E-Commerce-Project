package com.csi.services;

import java.util.List;

import com.csi.utils.entities.Seller;

public interface SellerService {
	void registerSeller(Seller seller);
	List<Seller> allSellers();
	Seller findById(int id);
	Seller validate(String userid,String pwd);
	void deleteSeller(int id);

	List<Seller> findSellersByName(String name);

	List<Seller> sortSellerByName();
	
}
