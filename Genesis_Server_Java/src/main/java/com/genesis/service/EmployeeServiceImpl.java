package com.genesis.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genesis.dao.EmployeeDAO;
import com.genesis.model.Employee;
import com.genesis.validator.Validator;

@Service(value="employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Integer addEmployee(Employee employee) throws Exception {
		System.out.println("Validating the user................. Please wait!");
		
		try {
			Validator.validate(employee);
		} catch (NullPointerException e) {
			throw new Exception ("Service.NULLPOINTEREXCEPTION_FROM_VALIDATOR");
		}
		
		Integer retValue = 0;
		
		try {
			retValue = employeeDAO.addEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("EmployeeService: "+e.getMessage());
			throw new Exception("Service.UKNOWN_EXCEPTION");
		}
		
		if(retValue == 0) {
			throw new Exception("Service.INVALID_SERVICE_ERROR_ADD_USER");
		}
		
	    if(retValue == -1) {
			throw new Exception("Service.USER_ALREADY_EXISTS");
		}
		
		return retValue;
	}

	@Override
	public Employee getEmployee(Integer employeeId) throws Exception {
		Employee employee = null;
		
		employee = employeeDAO.getEmployee(employeeId);	
		
		if(employee == null) {
			throw new Exception("Service.USER_DOESNT_EXIST");
		}
		
		return employee;
	}

	@Override
	public Integer updateEmployee(Integer employeeId, Employee employee) throws Exception {
		
		Integer idEmployee = employeeDAO.updateEmployee(employeeId, employee);
		
		if(idEmployee == 0) {
			throw new Exception("Service.USER_EMAIL_UPDATE_ERROR");
		}
		
		return idEmployee;
	}

	@Override
	public Integer deleteEmployee(Integer employeeId) throws Exception {
		
		Integer idEmployee= employeeDAO.deleteEmployee(employeeId);
		
		if(idEmployee == -1 || idEmployee == 0) {
			throw new Exception("Service.USER_DOESNT_EXIST");
		}
		
		return idEmployee;
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employeeList = employeeDAO.getAllEmployees();
		
		if(employeeList.isEmpty()) {
			throw new Exception("Service.NO_USER_FOUND");
		}
		
		return employeeList;
	}
	
	@Override
	public Integer clockIn(Employee employee, String date, String timeIn) throws Exception {
		Integer updateCount = null;
		
		/*
		 * get user time card using date and id
		 * then add time in information
		 * post it back
		 */

		updateCount = employeeDAO.clockIn(employee, date, timeIn);
		
		
		return updateCount;
	}
	
	@Override
	public Integer clockOut(Integer employeeId, String date, String timeOut) throws Exception {
		Integer updateCount = null;
		
		/*
		 * get user time card using date and id
		 * then add time in information
		 * post it back
		 */

		updateCount = employeeDAO.clockOut(employeeId, date, timeOut);
		
		
		return updateCount;
	}

}
