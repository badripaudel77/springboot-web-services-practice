package io.badri.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.badri.app.entity.User;
import io.badri.app.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		CustomeUserDetails userDetails = null;
		if (user != null) {
			userDetails = new CustomeUserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("User do not exists with given name : " + username);
		}
		return userDetails;

	}

}
