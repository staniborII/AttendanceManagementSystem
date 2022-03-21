package com.genesis.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.genesis.model.Employee;

public interface EmployeeService {
	
	public Integer addEmployee(Employee employee) throws Exception;
	public Employee getEmployee(Integer employeeId) throws Exception;
	public Integer updateEmployee(Integer employeeId, Employee employee) throws Exception;
	public Integer deleteEmployee(Integer employeeId)throws Exception;
	public List<Employee> getAllEmployees() throws Exception;
	public Integer clockIn(Employee employee, String date, String timeIn) throws Exception;
	public Integer clockOut(Integer employeeId, String date, String timeOut) throws Exception;

}
