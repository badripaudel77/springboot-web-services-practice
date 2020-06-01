package io.badri.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.badri.app.entity.Product;

public interface ProductService {

	public void saveProduct(Product product,int userId);
	
	public void findByUserId(Long id);
	
	public boolean updateProduct(Product product, int userId, Long pId);
	
	public boolean deleteProduct(int userId, Long pId);
	
	//to get all the products of one user [userId]
	public Page<Product> getAllProductsByUserId(int userId,Pageable pageable);
	
	//find all the products in table [for admin ]
	public List<Product> getAllProducts();

	
}
