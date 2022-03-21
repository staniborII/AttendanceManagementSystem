package com.genesis.validator;

import com.genesis.model.Employee;

public class Validator {
	
	public static void validate(Employee employee) throws Exception {
		
		String errorMessage = null;
		
		if(validateUserId(employee.getEmployeeId()) == false) {
			errorMessage = "Validator.INVALID_USER_ID";
		}
		
		if(validateUserName(employee.getFirstName(),employee.getLastName()) == false) {
			errorMessage = "Validator.INVALID_USER_NAME";
		}
		
		if(validateDesignation(employee.getDesignation()) == false) {
			errorMessage = "Validator.INVALID_USER_DESIGNATION";
		}
		
		if(validateRFID(employee.getRfid()) == false) {
			errorMessage = "Validator.INVALID_USER_RFID";
		}
		
		if (errorMessage != null) {
			Exception exception = new Exception(errorMessage);
			throw exception;
		}
		
		
	}

	public static boolean validateRFID(Integer rfid) {

		String rfidUser = rfid.toString();
		boolean result = false;
		
		if(rfidUser.length() == 5) {
			result = true;
		}
		
		return result;
	}

	public static boolean validateDesignation(String designation) {
		boolean result = false;
		
		if(designation.length()>20 == false) {
			result = true;
		}
		
		return result;
	}

	public static boolean validateUserName(String firstName, String lastName) {
		boolean result = false;
		
		if(firstName.matches("[a-zA-Z]+") && lastName.matches("[a-zA-Z]+")) {
			result = true;
		}
		
		return result;
	}

	public static boolean validateUserId(Integer employeeId) {
		// user id has to be 3 digits
		String idEmployee = employeeId.toString();
		boolean result = false;
		
		if(idEmployee.length() < 4) {
			result = true;
		}
		return result;
	}
}
