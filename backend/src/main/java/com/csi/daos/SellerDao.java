package com.csi.daos;

import com.csi.utils.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao extends JpaRepository<Seller, Integer> {
	
	Seller findByUserid(String userid);

	//Seller findByUserName(String name);

}
