package io.badri.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.badri.app.entity.User;
import io.badri.app.service.UserService;

@RestController
@RequestMapping("/userlogin")
public class UserLoginController {

	private UserService userService;

	@Autowired
	public UserLoginController(UserService userService) {
	
		this.userService = userService;
	}
	
	@ResponseBody
	@PostMapping("/postlogin") // users/login
	public String userLogin(@RequestBody User user) {
		
	boolean isRegistered = userService.getLoggedinUser(user);
	  
	 if(isRegistered) {	
		
		 return "login";
	  }
	 else return "doesn't exist";
	}
}
