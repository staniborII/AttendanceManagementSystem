package com.genesis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genesis.dao.EmployeeDAO;
import com.genesis.dao.UserLoginDAO;
import com.genesis.entity.Role;
import com.genesis.model.Employee;
import com.genesis.model.UserLogin;
import com.genesis.validator.ValidatorUserLogin;

@Service(value="userLoginService")
@Transactional
public class UserLogInServiceImpl implements UserLoginService {
	
	@Autowired
	UserLoginDAO userLoginDAO;
	

	@Override
	public UserLogin authenticateUser(UserLogin userLogin) throws Exception {
		//This method calls the dao to querry db for user pword
		UserLogin userLoginFE = new UserLogin();//Login data to send to front end
		
		String userName = userLogin.getUserName();
		//String result = " ";
		
		UserLogin ulDAO = userLoginDAO.getUserCredentials(userName);
		
		if(!(ulDAO.getPassword().equals(userLogin.getPassword()))) {
			throw new Exception("Service.LOG_IN_FAILED");
		}
		
		/*
		 * In the if clauses below, we set loginId = employeeId,
		 * so that on the FE we can use this to retrieve employee details
		 * from db
		 */
		if(ulDAO.getRole() == Role.ADMIN) {
		
			userLoginFE.setUserName(ulDAO.getUserName());
			userLoginFE.setRole(ulDAO.getRole());
			userLoginFE.setEmployeeId(ulDAO.getEmployeeId());

			System.out.println("Employee id is:"+userLoginFE.getEmployeeId()+" executed from UserLoginServiceImpl");
			//result = "ADMIN";
		}
		
		if(ulDAO.getRole() == Role.USER) {
			
			userLoginFE.setUserName(ulDAO.getUserName());
			userLoginFE.setRole(ulDAO.getRole());
			userLoginFE.setEmployeeId(ulDAO.getEmployeeId());
			
			System.out.println("Employee id is:"+userLoginFE.getEmployeeId()+" executed from UserLoginServiceImpl");
			
			//result = "USER";
		}
		
		
		return userLoginFE;
	}

	@Override
	public String addUserLogin(UserLogin userLogin) throws Exception {
		//Calls the validator to validate the userName and Password
		//This method calls the dao to add new user login credentials to the db
	
		String result = " ";
		ValidatorUserLogin.validate(userLogin);
		
		Integer retValue = userLoginDAO.addUserLogin(userLogin);
		
		if(retValue == 0) {
			throw new Exception("Service.LOG_IN_DETAILS_NOT_PERSISTED");
		}
		if(retValue == 1) {
			result = "Success";
		}
		
		return result;
	}

	@Override
	public Integer deleteUserCredentials(Integer userId) throws Exception {
		Integer idUser = userLoginDAO.deleteUserCredentials(userId);
		
		if(idUser == -1 || idUser == 0) {
			throw new Exception("Service.USER_DOESNT_EXIST");
		}
		
		return idUser;
	}

	@Override
	public List<UserLogin> getAllUserCredentials() throws Exception {
		List<UserLogin> userList = userLoginDAO.getAllUserCredentials();
		
		if(userList.isEmpty()) {
			throw new Exception("Service.NO_USER_FOUND");
		}
		
		return userList;
	}

}
