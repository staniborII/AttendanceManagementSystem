/**
 * The Genesis BackEnd holds the business logic for the Genesis FrontEnd Application
 * 
 * @author Stan Ibor
 * @version 2.0
 * @since 08-08-2020
 */

package com.genesis;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.genesis.entity.Role;
import com.genesis.entity.UserGender;
import com.genesis.model.Address;
import com.genesis.model.Employee;
import com.genesis.model.TimeSheet;
import com.genesis.model.UserLogin;
import com.genesis.service.AddressService;
import com.genesis.service.EmployeeService;
import com.genesis.service.TimeSheetService;
import com.genesis.service.UserLoginService;

/*
 * TODO:
 * 1. One to Many Relationship between EMployee and TimeSheet
 * 2. Error Handling for Primary Key Violation and other errors
 * 3. JUnit testing
 */

@SpringBootApplication
public class GenesisBackEndApplication implements CommandLineRunner {
	
	@Autowired
	Environment environment;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserLoginService userLoginService;
	
	@Autowired
	TimeSheetService timeSheetService;
	
	@Autowired
	AddressService addressService;

	public static void main(String[] args) {
		SpringApplication.run(GenesisBackEndApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
	      
	      System.out.println("Genesis is online!!! :)");
	      System.out.println("Hello world from Command Line Runner");
	      
	      
	      //TO DO: 
	      //1. Add validations and sql script to ensure that the RFID is unique
	      //2. JUnit Test
	      
	      //addEmployee();
	      //findEmployee();
	      //getAllEmployees();
	      //deleteEmployee();
	      
	      //Implement method below
	      //updateUserEmail();
	      
	      //login();
	      //addLogin();
	      
	      //addTimeSheet();
	      //addAddress();
	      
	      //clockIn();
	      //clockOut();
	      
	   }
	
	
	public void clockIn() {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setEmployeeId(3);
		String date = LocalDate.now().toString();
		String timeIn = LocalTime.now().toString();
		
		try {
			Integer updateCount = employeeService.clockIn(emp, date, timeIn);
			System.out.println("Clock in time successfully updated for employee with id: "+emp.getEmployeeId()+" with "+updateCount+" rows updated");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
	}
	
	public void clockOut() {
		// TODO Auto-generated method stub
		Integer employeeId = 3;
		String date = LocalDate.now().toString();
		String timeOut = LocalTime.now().toString();
		
		try {
			Integer updateCount = employeeService.clockOut(employeeId, date, timeOut);
			System.out.println("Clock out time successfully updated for employee with id: "+employeeId+" with "+updateCount+" rows updated");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
	}

	public void addTimeSheet() {
		try {			
			TimeSheet t = new TimeSheet();
			t.setAttendanceDate(LocalDate.now().toString());
			//t.setEmployeeId();
			//t.setTimeId(timeId);
			t.setTimeIn(LocalTime.now().toString());
			t.setTimeOut(LocalTime.now().toString());
			t.setComment("Works");
			
			
			List<TimeSheet> tList = new ArrayList<TimeSheet>();
			
			
			tList.add(t);
			//Integer retValue = timeSheetService.addTimeSheet(t);
			
			//addEmployee();
			//Integer retValue = userService.addEmployee(employee);
			
			
			//System.out.println(retValue);
			
			
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
	}
	
	public void addLogin() {
		try {
			Employee employee = new Employee();
			employee.setEmployeeId(3);
			employee.setFirstName("Cynthia");
			employee.setLastName("Moore");
			employee.setDesignation("C.O.O");
			employee.setEmailId("cm@hotmail.com");
			employee.setRfid(77778);
			employee.setUserGender(UserGender.FEMALE);
			employee.setRegDate(LocalDate.now().toString());
			employee.setPhoneNumber(12345678901L);
			
			UserLogin u = new UserLogin();
			u.setEmployeeId(employee.getEmployeeId());
			u.setUserName("user2");
			u.setPassword("user346");
			u.setRole(Role.USER);
			
			String retValue = userLoginService.addUserLogin(u);
			System.out.println(retValue);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
		
	}
	
	public void addAddress() {
		try {
			Employee employee = new Employee();
			employee.setEmployeeId(3);
			employee.setFirstName("Cynthia");
			employee.setLastName("Moore");
			employee.setDesignation("C.O.O");
			employee.setEmailId("cm@hotmail.com");
			employee.setRfid(77778);
			employee.setUserGender(UserGender.FEMALE);
			employee.setRegDate(LocalDate.now().toString());
			employee.setPhoneNumber(12345678901L);
			
			Address address = new Address();
			address.setCity("Cologne");
			address.setState("NRW");
			address.setStreetName("Am Hauptbahnhof");
			address.setStreetNo(1);
			address.setZip(50766);
			address.setEmployeeId(employee.getEmployeeId());
			
			employee.setAddress(address);
			
			String retValue = addressService.addAddress(address);
			System.out.println(retValue);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
		
	}

	public void login() {
		/*
		 * needed for successful login are:
		 * employeeId
		 * userName
		 * passWord
		 * The employeeId is used as primary key because
		 * the String userName when used as primary key 
		 * throws a NumberFormatException when querrying the database
		 * to retrieve an entry
		 */
		
		try {
			UserLogin u = new UserLogin();
			//Catch error when the user login profile does not exist
			
			//u.setEmployeeId(1);
			u.setUserName("user1");
			u.setPassword("user12345");
			//u.setRole(Role.ADMIN);
			
			UserLogin retValue = userLoginService.authenticateUser(u);
			System.out.println(retValue.getUserName()+" successfully logged in");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
	}
	
	//Method needs implementation
	public void updateUserEmail() {
		
		
		
	}

	public void addEmployee() {
		
		try {
			Employee employee = new Employee();
			employee.setEmployeeId(4);
			employee.setFirstName("Xavier");
			employee.setLastName("Axel");
			employee.setDesignation("PA");
			employee.setEmailId("xa@hotmail.com");
			employee.setRfid(77895);
			employee.setUserGender(UserGender.MALE);
			employee.setRegDate(LocalDate.now().toString());
			employee.setPhoneNumber(123456789L);

			TimeSheet t = new TimeSheet();
			t.setAttendanceDate(LocalDate.now().toString());
			t.setEmployeeId(employee.getEmployeeId());
			//t.setTimeId(timeId);
			t.setTimeIn(LocalTime.now().toString());
			t.setTimeOut(LocalTime.now().toString());
			t.setComment("Works");
			
			List<TimeSheet> tList = new ArrayList<TimeSheet>();		
			tList.add(t);
			
			
			UserLogin u = new UserLogin();
			u.setRole(Role.USER);
			u.setEmployeeId(employee.getEmployeeId());
			System.out.println("Employee Id in login table from main class: "+u.getEmployeeId());
			u.setUserName("user444");
			u.setPassword("user222");
			
			Address address = new Address();
			address.setCity("Cologne");
			address.setState("NRW");
			address.setStreetName("Am Butzweiler Platz");
			address.setStreetNo(3);
			address.setZip(50788);
			address.setEmployeeId(employee.getEmployeeId());
			
			employee.setTimeSheets(tList);
			employee.setUserLogin(u);
			employee.setAddress(address);
			
			System.out.println("User with id: "+ employee.getEmployeeId()+" is being added. Please wait!.......");
			
			Integer employeeId = employeeService.addEmployee(employee);
			
			System.out.println(environment.getProperty("UserInterface.USER_ADDED_SUCCESS") + " " + employeeId);

			
			System.out.println("USER DETAILS");
			System.out.println("=====================");
			System.out.println("UserId\t\tFirstName\tLastName\tEmail\t\tGender\t\tDesignaiton\t\tCity\t\tRegistration Date and TIme");
			System.out
					.println("=======================================================================================================================================");
			System.out.println(employee.getEmployeeId() + "\t\t"
					+ employee.getFirstName() + "\t\t"
					+ employee.getLastName() + "\t\t"
					+ employee.getEmailId() + "\t\t"
					+ employee.getUserGender() + "\t\t" 
					+ employee.getDesignation() + "\t\t"
					+ employee.getAddress().getCity() + "\t\t"
					+ employee.getRegDate());
			
		} catch (Exception e) {
			
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
		
	}
	
	public void findEmployee() {
		
		try {
			
			Employee employee = employeeService.getEmployee(2);
		
			System.out.println(environment.getProperty("UserInterface.USER_FOUND_SUCCESS") + " " + employee.getEmployeeId());

			System.out.println("USER DETAILS");
			System.out.println("=====================");
			System.out.println("UserId\t\tUserName\tGender\t\tDesignation");
			System.out
					.println("========================================================================");
			System.out.println(employee.getEmployeeId() + "\t\t"
					+ employee.getFirstName() + "\t\t"
					+ employee.getUserGender() + "\t\t"
					+ employee.getDesignation());
			
			List<TimeSheet> t = new ArrayList<TimeSheet>();
			t = employee.getTimeSheets();
			
			for(TimeSheet timeSheets:t) {
				System.out.println(timeSheets.getAttendanceDate());
			}
			
			
			
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
	}
	
	public void getAllEmployees() {
		try {
			List<Employee> employees = employeeService.getAllEmployees();
			
			System.out.println("USER DETAILS");
			System.out.println("=====================");
			System.out.println("UserId\t\tFirstName\tLastName\tEmail\t\t\tGender\t\tRfid\t\tDesignation");
			System.out
					.println("==================================================================================================================");
			for (Employee employee : employees) {
				System.out.println(employee.getEmployeeId() + "\t\t"
						+ employee.getFirstName() + "\t\t"
						+ employee.getLastName() + "\t\t"
						+ employee.getEmailId() + "\t\t"
						+ employee.getUserGender() + "\t\t"
						+ employee.getRfid()+ "\t\t"
						+ employee.getDesignation());
			
			}
		} catch (Exception e) {
			// e.printStackTrace();
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}

	}
	
	public void deleteEmployee() {
		try {
			Integer employeeId = employeeService.deleteEmployee(2);
			
			System.out.println("User with id: "+employeeId+" has been successfully deleted");
			
		} catch (Exception e) {
			// e.printStackTrace();
			String message = environment.getProperty(e.getMessage());
			if (message == null) {
				message = environment.getProperty("General.EXCEPTION");
			}
			System.out.println("ERROR:" + message);
		}
		
	}
}
