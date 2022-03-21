package com.genesis.dao;

import java.util.List;

import com.genesis.model.UserLogin;

public interface UserLoginDAO {
	public Integer addUserLogin(UserLogin userLogin) throws Exception;
	public UserLogin getUserCredentials(String userName)throws Exception;
	public Integer deleteUserCredentials(Integer userId) throws Exception;
	public List<UserLogin> getAllUserCredentials() throws Exception;
}
