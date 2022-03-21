package com.genesis.service;

import java.util.List;

import com.genesis.model.UserLogin;

public interface UserLoginService {
	public String addUserLogin(UserLogin userLogin)throws Exception;
	public UserLogin authenticateUser(UserLogin userLogin) throws Exception;
	public Integer deleteUserCredentials(Integer userId)throws Exception;
	public List<UserLogin> getAllUserCredentials() throws Exception;
}
