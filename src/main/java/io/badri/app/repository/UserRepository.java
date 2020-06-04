package io.badri.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.badri.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username); //null obj
	
	public User findByEmail(String email);
	
	public User findByPassword(String password);
	
//	@Query("select u from User u where u.id = :id")
	public User findById(int id);
        
	public List<User> findByDob(LocalDate dob);
			
}
