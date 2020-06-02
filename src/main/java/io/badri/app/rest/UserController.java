package io.badri.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.badri.app.entity.User;
import io.badri.app.repository.UserRepository;
import io.badri.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	// di , use either filed or constructor injection [ most popular one , even though we alos have others ]
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	//@PreAuthorize(value = "")
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{userId}")  
	public void updateUser(@PathVariable(value = "userId") Integer userId, @RequestBody User user) {
		//update 
		boolean b = userService.updateUser(user,userId);
		if(b) System.out.println("updated");
		else System.out.println("failed");
		//users/1
	}
	

	@GetMapping
	public List<User> getAllUsers() {
		
		return userService.listUser();
	}
	
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable(value = "userId") int userId) {
	
		return userRepo.findById(userId);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable(value = "userId") int userId) {
		
		userService.deleteUser(userId);
	}
	
}
