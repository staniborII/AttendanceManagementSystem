package com.genesis;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.genesis.dao.EmployeeDAOImpl;
import com.genesis.entity.UserGender;
import com.genesis.model.Employee;
import com.genesis.service.EmployeeServiceImpl;
import com.genesis.validator.Validator;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class GenesisBackEndApplicationTests {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Mock
	private EmployeeDAOImpl employeeDAO;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServcie;
	
	
	@Test
	public void userIdÍnvalidTest() throws Exception {
		/*
		 * Checks, using the validator if the users details are correctly entered, according to the conditions
		 * stated in the validator.
		 * 
		 * Test cases are passed if an exception is thrown and failed if no exception is thrown
		 */
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setFirstName("Se2hnse");
		employee.setLastName("Connor");
		employee.setDesignation("C.O.O");
		employee.setEmailId("sc@hotmail.com");
		employee.setRfid(55555);
		employee.setUserGender(UserGender.FEMALE);
		
		//expectedException.expect(Exception.class);
		//expectedException.expectMessage("Validator.INVALID_USER_NAME");
		//Validator.validate(user);
		
		//Code below works if you exclude junit vintage from pom.xml
		Assert.assertThrows("Should throw an exception if any of the user details is invalid",Exception.class,() -> Validator.validate(employee));
				
	}
	


	//Read up on mockito junit5 testing exception
//	@Test
//	public void userDoesNotExistsTest() throws Exception {
//		expectedException.expect(Exception.class);
//		expectedException.expectMessage("Service.USER_DOESNT_EXISTS");
//		
//		User user = new User();
//		user.setUserId(3);
//		user.setFirstName("Kassa");
//		user.setLastName("Gordo");
//		user.setDesignation("C.E.O");
//		user.setEmailId("kg@hotmail.com");
//		user.setRfid(12345);
//		user.setUserGender(UserGender.MALE);
//				
//		//when().thenReturn();
//		Mockito.when(userDAO.getUser(user.getUserId())).thenThrow(null);
//		
//		userServcie.getUser(user.getUserId());
//	}

}
