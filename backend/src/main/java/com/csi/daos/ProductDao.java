package com.csi.daos;

import java.util.List;

import com.csi.utils.entities.Product;
import com.csi.utils.entities.Seller;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	List<Product> findBySeller(Seller sellerId, Sort sort);
	List<Product> findByPcatAndSubcat(String pcat,String subcat,Sort sort);

	List<Product> findByPname(String pname);
}
