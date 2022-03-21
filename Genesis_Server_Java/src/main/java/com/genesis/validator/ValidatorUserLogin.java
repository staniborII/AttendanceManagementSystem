package com.genesis.validator;

import com.genesis.model.UserLogin;

public class ValidatorUserLogin {
	
	public static void validate(UserLogin userLogin) throws Exception {
		
		String errorMessage = null;
		
		if(validatePassword(userLogin.getPassword()) == false) {
			errorMessage = "Validator.INVALID_USER_PASSWORD";
		}
		
		if(validateUserName(userLogin.getUserName()) == false) {
			errorMessage = "Validator.INVALID_U_NAME";
		}
		
		if (errorMessage != null) {
			Exception exception = new Exception(errorMessage);
			throw exception;
		}
		
		
	}

	private static boolean validatePassword(String password) {
		//password must contain letters a-zA-Z and at least one digit 0-9
		boolean result = false;
		
		String regex = "^(?=.*\\d)(?=.*[a-zA-Z]).{4,8}$";
		
		if(password.matches(regex)) {
			result = true;
		}
		
		return result;
	}

	private static boolean validateUserName(String userName) {
		//username can be alphanumeric but must be between 4 to 10 characters
		String regex = "[a-zA-Z0-9]{4,10}";
		boolean result = false;
		
		if(userName.matches(regex)) {
			result = true;
		}
		
		return result;
	}

}
