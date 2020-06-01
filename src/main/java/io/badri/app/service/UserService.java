package io.badri.app.service;

import java.util.List;

import io.badri.app.entity.User;

public interface UserService {

	public void saveUser(User user); //null obj
	
	public boolean updateUser(User user,int id);
	
	public boolean deleteUser(int id);
	
	public List<User> listUser();
	
	public boolean getLoggedinUser(User user);

   public void verifyAccount(String  userToken);
	
}
