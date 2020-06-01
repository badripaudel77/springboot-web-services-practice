package io.badri.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import io.badri.app.config.TwilioConfig;
import io.badri.app.entity.Product;
import io.badri.app.entity.User;
import io.badri.app.repository.ProductRepository;
import io.badri.app.repository.UserRepository;
import io.badri.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private TwilioConfig twilioConfig;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	//save product associated with its userId
	@Override
	public void saveProduct(Product product, int userId) {
	
	   User user = userRepo.findById(userId);
	   
 	   String  userPhone = user.getPhoneNumber();
 	   
 	   product.setUser(user);
       productRepo.save(product);
       
       //thank you for shopping with us. Send sms
       //to, from , message
   	MessageCreator creator = 	Message.creator(
   				          new PhoneNumber(userPhone),
   				          new PhoneNumber(twilioConfig.getTrialNumber()), 
   			              "Thank you " + user.getFirstName() + " for shopping with us !!"
   			            );
   	
   	creator.create(); //sends the message
	}	
    
	//find all product associated with its userId [ your own product ]
	@Override
	public void findByUserId(Long id) {
		productRepo.findById(id); 		
	}

	//update your own product
	@Override
	public boolean updateProduct(Product product, int userId, Long pId) {
		if(!userRepo.existsById(userId)) {
            throw new RuntimeException("User with userId " + userId + " not found");
        }
		if(!productRepo.existsById(pId)) {
            throw new RuntimeException("Product with productId " + pId + " not found");
        }
		   //get user
		   User user = userRepo.findById(userId);
			
		   product.setPid(pId); //same product id
		   user.setId(userId); //same user id

	 	   product.setUser(user);
	       productRepo.save(product);
		
		return true;
}

	//delete your product
	@Override
	public boolean deleteProduct(int userId, Long pId) {
		if(!userRepo.existsById(userId)) {
            throw new RuntimeException("User with userId " + userId + " not found");
		}
		 
		if(!productRepo.existsById(pId)) {
            throw new RuntimeException("Product with productId " + pId + " not found");
        }
		
		productRepo.deleteById(pId);
			 return true;
		}

	@Override
	public List<Product> getAllProducts() {

		return productRepo.findAll();
	}

	@Override
	public Page<Product> getAllProductsByUserId(int userId, Pageable pageable) {
		
		return  productRepo.findByUserId(userId, pageable);
	}
}

