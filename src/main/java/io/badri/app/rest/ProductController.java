package io.badri.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.badri.app.entity.Product;
import io.badri.app.service.ProductService;

@RestController()
@RequestMapping("/users")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/{userId}/products") //user will be able to save product associated with its userid
	
	public String saveProduct(@PathVariable(value = "userId") int userId, @RequestBody Product product) {

		productService.saveProduct(product, userId);
		return "Products with associated user id saved ";

	}
	
	@PutMapping("/{userId}/products/{pId}")
	public String updateYourProduct(@RequestBody Product product, @PathVariable(value = "userId") int userId, @PathVariable(value = "pId") Long pId) {
		
		boolean updateSuccess = productService.updateProduct(product, userId, pId);
		if(!updateSuccess) {
			return "couldn't be updated ";
		}
		return "Updated Successfully ";
	}
	
	@DeleteMapping("/{userId}/products/{pId}")
	public String deleteProduct(@PathVariable(value = "userId") int userId, @PathVariable(value = "pId") Long pId) {
		
		boolean deleteSuccess = productService.deleteProduct(userId, pId);
		
		if(!deleteSuccess) {
			
			return " Item couldn't be Deleted ";
		}
		
		return " Item Deleted Successfully ";
	}
	
	//get all the associated products to the particular user [userId]
	@GetMapping("/{userId}/products")
	public Page<Product> getAllProductsByUserId(@PathVariable(value = "userId") int userId,Pageable pageable) {
    
	  return productService.getAllProductsByUserId(userId, pageable); 
    	
    }
	
	
	// getting all the details of all the products [ for eg : for admin ]
	@GetMapping("/products")
	public List<Product> getAllProducts() {		
		List<Product> products = productService.getAllProducts();
		
		return products;
	}
}
