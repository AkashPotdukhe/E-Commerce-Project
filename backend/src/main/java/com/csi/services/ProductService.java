package com.csi.services;

import java.util.List;

import com.csi.utils.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
	void addProduct(Product p, MultipartFile pic);
	List<Product> findProducts(int sellerId);
	void updateProduct(Product p);
	void deleteProduct(int prodid);
	List<Product> allProducts();
	List<Product> categoryProducts(String pcat,String subcat);
	Product findProductById(int prodid);
	Page<Product> allProductsPaginated(int page,int pagesize);

	List<Product> findProductByName(String pname);

	List<Product> sortProductByPrice();


	List<Product> filterProductByPrice(int price);

	List<Product> filterByProductCatagory(String pcat);

	List<Product> filterProductBySubCatagory(String subcat);

	List<Product> sortProductByBrands();

	List<Product> fetchProductWithSecodHighestPrice();
}
