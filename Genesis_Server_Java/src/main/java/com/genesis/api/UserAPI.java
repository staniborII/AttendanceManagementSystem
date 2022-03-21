package com.genesis.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.genesis.entity.Role;
import com.genesis.model.Employee;
import com.genesis.model.TimeSheet;
import com.genesis.model.UserLogin;
import com.genesis.service.EmployeeService;
import com.genesis.service.UserLoginService;
import com.genesis.utility.UserActivityLog;

@CrossOrigin
@RestController
@RequestMapping(value="/genesis")
public class UserAPI {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private Environment environment;
	
	//User Credentials API
	@GetMapping(value="/userCredentials")
	public ResponseEntity<List<UserLogin>> getAllUserCredentials() throws Exception{
		try {
			List<UserLogin> userLoginList = userLoginService.getAllUserCredentials();
			ResponseEntity<List<UserLogin>> response = new ResponseEntity<List<UserLogin>>(userLoginList, HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}		
	}
	
	@DeleteMapping(value="/userCredentials/{employeeId}")
	public ResponseEntity<String> deleteUserCredentials(@PathVariable Integer employeeId) throws Exception{
		try {
			userLoginService.deleteUserCredentials(employeeId);
			String sucessMessage = "User with id: "+employeeId+" has been deleted successfully";
			ResponseEntity<String> response = new ResponseEntity<String>(sucessMessage, HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	@PostMapping(value="/newUser")
	public ResponseEntity<String> addUserLogin(@RequestBody UserLogin userLogin) throws Exception{
		/*
		 * This method adds a new user log in profile
		 */
		
		try {
			userLoginService.addUserLogin(userLogin);
			String successMessage = "User login profile successfully created";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
			
			return response;
			
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<UserLogin> login(@RequestBody UserLogin userLogin) throws Exception{
		
		try {
			UserLogin retValue = userLoginService.authenticateUser(userLogin);
			
			System.out.println("Log in API works: "+retValue.getUserName());
			System.out.println("Role: "+retValue.getRole());
			
			UserActivityLog u = new UserActivityLog();
			u.logActivity(userLogin.getUserName()+" with employee number "+userLogin.getEmployeeId()+" loggeed in");
			
			//if user login is successful, send the role as a response
			ResponseEntity<UserLogin> response = new ResponseEntity<UserLogin>(retValue,HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			System.out.println(environment.getProperty(e.getMessage()));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	
	/*
	 * The methods below processes the request from a clients URI and returns
	 * response according to the request
	 */
	@GetMapping(value="/employees")
	public ResponseEntity<List<Employee>> getAllUserDetails() throws Exception{
		try {
			List<Employee> employeeList = employeeService.getAllEmployees();
			ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}		
	}
	
	/*
	 * The method below processes the request from a clients URI and returns
	 * the customer whose id matches the userId given as a parameter
	 */
	@GetMapping(value="/employees/{employeeId}")
	public ResponseEntity<Employee> getUserDetails(@PathVariable Integer employeeId) throws Exception{
		
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	//HTTP post method creates a new row in db
	/*
	 * We know that along with the POST request we have to send user data. 
	 * For this customer parameter of addUser() method is annotated with @RequestBody  annotation. 
	 * It ensures that JSON data about user in the request body is converted to a User object.
	 */
	@PostMapping(value="/employees")
	public ResponseEntity<String> addUser(@RequestBody Employee employee) throws Exception{
		try {
			LocalDate date = LocalDate.now();
			DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			String formattedDate = date.format(format1);
			
			employee.setRegDate(formattedDate);
			
			//On adding a new employee, the time sheets and login profile are created
			TimeSheet t = new TimeSheet();
			List<TimeSheet> tlist = new ArrayList<TimeSheet>();
			tlist.add(t);
			employee.setTimeSheets(tlist);
			
			UserLogin userLogin = new UserLogin();
			userLogin.setUserName(employee.getFirstName()+employee.getLastName()+employee.getEmployeeId());
			userLogin.setPassword(employee.getLastName()+"123");//generate a new password when a new user is added
			
			/*This if condition is if an admin can be added from the registration form*/
			if(employee.getRole()!=null) {
				userLogin.setRole(employee.getRole());
			}else {
				userLogin.setRole(Role.USER);
			}
			
			
			employee.setUserLogin(userLogin);
			
			employeeService.addEmployee(employee);
			String successMessage = "Employee with id: "+employee.getEmployeeId()+" has been added successfully";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	@PutMapping(value="/employees/clockIn/{employeeId}")
	public ResponseEntity<String> clockIn(@PathVariable  Integer employeeId) throws Exception{
		try {
			
			String retValue = "";
			String clockInDate = LocalDate.now().toString();
			String clockInTime = LocalTime.now().toString();
			
			Employee e = new Employee();
			e.setEmployeeId(employeeId);
			
			Integer updateCount = employeeService.clockIn(e, clockInDate, clockInTime);
			
			if(updateCount > 0) {
				retValue = "Employee with id:"+employeeId+" has succesfully clocked in.\n Clock in time:"+clockInTime+"\n Date:"+clockInDate;
			}else {
				retValue = "Clock in not successful";
			}
		
			ResponseEntity<String> response = new ResponseEntity<String>(retValue,HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	@PostMapping(value="/employees/clockOut")
	public ResponseEntity<String> clockOut(@PathVariable Integer employeeId) throws Exception{
		try {
			String retValue = "";
			String clockOutDate = LocalDate.now().toString();
			String clockOutTime = LocalTime.now().toString();
			
			
			Integer updateCount = employeeService.clockOut(employeeId, clockOutDate, clockOutTime);
			
			if(updateCount > 0) {
				retValue = "Employee with id:"+employeeId+" has succesfully clocked out.\n Clock in time:"+clockOutTime+"\n Date:"+clockOutDate;
			}else {
				retValue = "Clock out not successful";
			}
		
			ResponseEntity<String> response = new ResponseEntity<String>(retValue,HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	

	//HTTP put method updates a row in db
	@PutMapping(value="/employees/{employeeId}")
	public ResponseEntity<String> updateUser(@PathVariable Integer employeeId, @RequestBody Employee employee) throws Exception{
		try {
			employeeService.updateEmployee(employeeId, employee);
			String successMessage = "Email address of user with id: "+employee.getEmployeeId()+" has been successfully updated";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	
	@DeleteMapping(value="/employees/{employeeId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer employeeId) throws Exception{
		try {
			employeeService.deleteEmployee(employeeId);
			String sucessMessage = "User with id: "+employeeId+" has been deleted successfully";
			ResponseEntity<String> response = new ResponseEntity<String>(sucessMessage, HttpStatus.OK);
			
			return response;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()),e);
		}
	}
	


}
