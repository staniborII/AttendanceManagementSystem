package com.genesis.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.genesis.entity.AddressEntity;
import com.genesis.entity.EmployeeEntity;
import com.genesis.entity.TimeSheetEntity;
import com.genesis.entity.UserLoginEntity;
import com.genesis.model.Address;
import com.genesis.model.Employee;
import com.genesis.model.TimeSheet;
import com.genesis.model.UserLogin;


/*
 * This class is annotated with @Repository annotation, 
 * so that Spring automatically scans this class and registers it 
 * as the Spring bean. 
 * The EntityManager object is injected using @PersistenceContext annotation.
 */

@Repository(value = "employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * To persist an entity to the db, object is created and its initial state is new. 
	 * When the persist() method is called it associates this object with EntityManager and changes its state from new to managed state. 
	 * The values stored in this managed entity object will be inserted in the database when the transaction is committed. 
	 * The persist() method will throw IllegalArgumentException if the argument is not an instance of an entity class. 
	 * Only instances of entity classes can be stored in the database independently. 
	 * If the database already contains another entity of the same type with the same primary key, then EntityExistsException is thrown. 
	 * This exception is thrown either by persist() (if that existing entity object is currently managed by the EntityManager) or by commit()
	 * 
	 * In the addMethod() below:
	 * - We check if the user exists. If user already exists, we set employee id to 1 and return this value, which is then handled by the Service class
	 * - else, we persist the user to database
	 */
	@Override
	public Integer addEmployee(Employee employee) throws Exception {
				
		Integer employeeId = null; 
		
		EmployeeEntity eEntity = entityManager.find(EmployeeEntity.class, employee.getEmployeeId());
		System.out.println(eEntity);
		if(eEntity != null) {
			System.out.println("In EmployeeDAO addUser Method: User already exists");
			employeeId = -1;
			return employeeId;
		}else {
			try {
				
				EmployeeEntity employeeEntity = new EmployeeEntity();
				employeeEntity.setEmployeeId(employee.getEmployeeId());
				employeeEntity.setFirstName(employee.getFirstName());
				employeeEntity.setLastName(employee.getLastName());
				employeeEntity.setDesignation(employee.getDesignation());
				employeeEntity.setEmailId(employee.getEmailId());
				employeeEntity.setUserGender(employee.getUserGender());
				employeeEntity.setRfid(employee.getRfid());
				employeeEntity.setPhoneNumber(employee.getPhoneNumber());
				employeeEntity.setRegDate(employee.getRegDate());
				
				List<TimeSheet> timeSheetToAllocate = employee.getTimeSheets();
				List<TimeSheetEntity> timeSheets = new LinkedList<>();
				
				for(TimeSheet timeSheet: timeSheetToAllocate) {
					TimeSheetEntity newTimeSheet = new TimeSheetEntity();
					newTimeSheet.setAttendanceDate(timeSheet.getAttendanceDate());
					newTimeSheet.setComment(timeSheet.getComment());
					newTimeSheet.setEmployeeId(timeSheet.getEmployeeId());
					newTimeSheet.setTimeIn(timeSheet.getTimeIn());
					newTimeSheet.setTimeOut(timeSheet.getTimeOut());
					timeSheets.add(newTimeSheet);
				}
				
				employeeEntity.setTimeSheets(timeSheets);
				
				System.out.println("EmployeeDAOImpl: Before creating login entity");
				
				UserLoginEntity userLoginEntity = new UserLoginEntity();
				//userLoginEntity.setRfid(employee.getUserLogin().getRfid());
				userLoginEntity.setEmployeeId(employee.getEmployeeId());
				userLoginEntity.setRole(employee.getUserLogin().getRole());
				userLoginEntity.setUserName(employee.getUserLogin().getUserName());
				userLoginEntity.setPassword(employee.getUserLogin().getPassword());
							
				employeeEntity.setUserLogin(userLoginEntity);
				
				System.out.println("EmployeeDAOImpl: Employee Login Entity Created....Next, Set Address");
				
				AddressEntity addressEntity = new AddressEntity();
				addressEntity.setEmployeeId(employee.getEmployeeId());
				addressEntity.setStreetName(employee.getAddress().getStreetName());
				addressEntity.setStreetNo(employee.getAddress().getStreetNo());
				addressEntity.setCity(employee.getAddress().getCity());
				addressEntity.setState(employee.getAddress().getState());
				addressEntity.setZip(employee.getAddress().getZip());		
				
				System.out.println("EmployeeDAOImpl: Employee Address Entity Created....Next, persist to database!");

				employeeEntity.setAddress(addressEntity);
				
				System.out.println(addressEntity.getCity());

				System.out.println("Before persisting to DB!");
				
				entityManager.persist(employeeEntity);
				employeeId = employeeEntity.getEmployeeId();
				
			} catch(ConstraintViolationException  c) {
				SQLException cause = (SQLException) c.getCause();
			    //evaluate cause and find out what was the problem
			    System.out.println("EmployeeDAOImpl:"+cause.getMessage());
				throw new Exception("Dao.VIOLATION");
			} catch(DuplicateKeyException dke) {
				System.out.println("EmployeeDAOImpl: Duplicate Key Exception");
				throw new Exception("Dao.ERROR_ADDING_USER");
			}
			
			catch (Exception e) {
				//Find a way to catch primary key violation constraint exception
				throw new Exception("Dao.ERROR_ADDING_USER");
			} 
		}
		
		
		
		return employeeId;	
	}
	
	/*
	 * Returns a single employee entity
	 */

	@Override
	public Employee getEmployee(Integer employeeId) throws Exception {
		Employee employee=null;	
		List<TimeSheet> timeSheets = new LinkedList<>();
		
		System.out.println("Fetching Employee.....");
		EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, employeeId);
		System.out.println(employeeEntity);
		
		if(employeeEntity!=null){
			employee=new Employee();
			employee.setEmployeeId(employeeEntity.getEmployeeId());
			employee.setFirstName(employeeEntity.getFirstName());
			employee.setLastName(employeeEntity.getLastName());
			employee.setEmailId(employeeEntity.getEmailId());
			employee.setUserGender(employeeEntity.getUserGender());
			employee.setDesignation(employeeEntity.getDesignation());
			employee.setRfid(employeeEntity.getRfid());
			employee.setPhoneNumber(employeeEntity.getPhoneNumber());
			
			
			try {
				//try-catch is used because of the toString() method used on the date
				//if the date is null, the toString method will throw a NullPointerException
				employee.setRegDate(employeeEntity.getRegDate().toString());
			} catch (Exception e) {
				System.out.println("Registration date is Empty");
			}
			
			List<TimeSheetEntity> timeSheetEntities = employeeEntity.getTimeSheets();
			System.out.println("Employee gotten, fetching timesheet");
			
			if(!timeSheetEntities.isEmpty()) {
				for(TimeSheetEntity timeSheetEntity: timeSheetEntities) {
					TimeSheet newTimeSheet = new TimeSheet();
					
//					if(timeSheetEntity.getAttendanceDate() != null) {
//						newTimeSheet.setAttendanceDate(timeSheetEntity.getAttendanceDate());
//					}
//					if(timeSheetEntity.getComment()!=null) {
//						newTimeSheet.setComment(timeSheetEntity.getComment());
//					}
//					if(timeSheetEntity.getEmployeeId()!=null){
//						newTimeSheet.setEmployeeId(timeSheetEntity.getEmployeeId());
//					}
//					if(timeSheetEntity.getTimeIn() != null) {
//						newTimeSheet.setTimeIn(timeSheetEntity.getTimeIn());
//					}
//					if(timeSheetEntity.getTimeOut()!=null) {
//						newTimeSheet.setTimeOut(timeSheetEntity.getTimeOut());
//					}
					
					newTimeSheet.setTimeId(timeSheetEntity.getTimeId());
					newTimeSheet.setEmployeeId(timeSheetEntity.getEmployeeId());
					newTimeSheet.setComment(timeSheetEntity.getComment());
					newTimeSheet.setAttendanceDate(timeSheetEntity.getAttendanceDate());
					newTimeSheet.setTimeIn(timeSheetEntity.getTimeIn());
					newTimeSheet.setTimeOut(timeSheetEntity.getTimeOut());
					//What if newTimeSheet is empty? 
					timeSheets.add(newTimeSheet);
				}
			}
			employee.setTimeSheets(timeSheets);
//			if(!timeSheets.isEmpty()) {
//				employee.setTimeSheets(timeSheets);
//			}
			
			UserLoginEntity userLoginEntity = employeeEntity.getUserLogin();
			UserLogin userLogin = new UserLogin();
			userLogin.setEmployeeId(userLoginEntity.getEmployeeId());
			userLogin.setUserName(userLoginEntity.getUserName());
			
			AddressEntity addressEntity = employeeEntity.getAddress();
			Address address = new Address();
			address.setCity(addressEntity.getCity());
			address.setState(addressEntity.getState());
			address.setStreetName(addressEntity.getStreetName());
			address.setStreetNo(addressEntity.getStreetNo());  
			address.setZip(addressEntity.getZip());
			
			employee.setAddress(address);
		}
		
		return employee;
		/*
		 * In above code, customer details are fetched using find() method based on primary key i.e. customerId. 
		 * The retrieved object need not to be casted to CustomerEntity because find() method returns an instance of the same class 
		 * that it takes as its first argument (using generics). 
		 * If there is already an entity object present in persistence context of entityManager then no retrieval from database is required and 
		 * the existing managed object is returned. Otherwise, the entity object is retrieved from the database which will be in managed state. 
		 */
	}

	//Check if updating the employee without updating the TimeSheet will nullify entries in the timesheet
	@Override
	public Integer updateEmployee(Integer employeeId, Employee employee) throws Exception {
		System.out.println("Updating Employee details......");
		
		Integer idEmp=0;
		EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, employeeId);
		
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
		employeeEntity.setEmailId(employee.getEmailId());
		employeeEntity.setDesignation(employee.getDesignation());
		employeeEntity.setRfid(employee.getRfid());
		employeeEntity.setUserGender(employee.getUserGender());
		employeeEntity.setPhoneNumber(employee.getPhoneNumber());
		
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity(employee.getAddress().getCity());
		addressEntity.setState(employee.getAddress().getState());
		addressEntity.setStreetName(employee.getAddress().getStreetName());
		addressEntity.setStreetNo(employee.getAddress().getStreetNo());
		addressEntity.setZip(employee.getAddress().getZip());
		
		employeeEntity.setAddress(addressEntity);
		
		idEmp=employeeEntity.getEmployeeId();
		
		return idEmp;
		
		/*
		 * In above code, the CustomerEntity object whose email has to be updated,
		 * is first retrieved from database, using the users id. 
		 * Then email id attribute of this entity object is updated with new email id. 
		 * The EntityManager automatically detects the change and database is updated when transaction is committed.
		 */
	}

	@Override
	public Integer deleteEmployee(Integer employeeId) throws Exception {
		
		try {
			EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, employeeId);
			
			if(employeeEntity.getEmployeeId() == employeeId) {
				entityManager.remove(employeeEntity);
				//userId=userEntity.getUserId();
				//if implemented, then the user exists
			}
			else {
				employeeId = -1;
				//User does not Exists
			}
			
		} catch (NullPointerException e) {
			//if there is a NullPointerException the the user does not exists
			employeeId = 0;
		}
		
		return employeeId;
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		String sql = "SELECT e FROM EmployeeEntity e";
		Query q = entityManager.createQuery(sql);
		
		List<EmployeeEntity> employeeList = q.getResultList();
		List<Employee> finalList = new ArrayList<>();
		
		for(EmployeeEntity employeeEntity:employeeList) {
						
			Employee employee = new Employee();
			Address address = new Address();
			//System.out.println(customer.getDvd().getDvdType().equals(dvdType));
			//System.out.println(customer.getDvd().getDvdType()+" "+dvdType);
			
			employee.setFirstName(employeeEntity.getFirstName());
			employee.setEmployeeId(employeeEntity.getEmployeeId());
			employee.setFirstName(employeeEntity.getFirstName());
			employee.setLastName(employeeEntity.getLastName());
			employee.setEmailId(employeeEntity.getEmailId());
			employee.setUserGender(employeeEntity.getUserGender());
			employee.setDesignation(employeeEntity.getDesignation());
			employee.setRfid(employeeEntity.getRfid());
			employee.setPhoneNumber(employeeEntity.getPhoneNumber());
			
			address.setCity(employeeEntity.getAddress().getCity());
			address.setState(employeeEntity.getAddress().getState());
			address.setStreetName(employeeEntity.getAddress().getStreetName());
			address.setStreetNo(employeeEntity.getAddress().getStreetNo());
			address.setZip(employeeEntity.getAddress().getZip());
			
			employee.setAddress(address);
			
			finalList.add(employee);
		}
		
		return finalList;
	}
	
	@Override
	public Integer clockIn(Employee employee, String date, String timeIn) throws Exception {
		Integer updateCount = null;
		
		/*
		 * Implement logic to create a new timesheet which will be needed every next day
		 * Retrieve employee time card and check the date on the card object with the new date
		 * If dates are equal throw exception (because with clock in we create a date)
		 * If dates are not equal create new timeSheet (which will be in a new row) and record date and time in
		 */
		
		/*
		 * on clock in date and clock in time are set
		 * get user time card using  id
		 * then add time in and date information
		 * post it back
//		 */
//		if(employee.getTimeSheets().isEmpty()) {
//			
//		}
//		
		try {
			
			//String sql = "UPDATE TimeSheetEntity t SET t.timeIn =?1 WHERE t.employeeId = ?2 AND t.attendanceDate =?3  ";
			String sql = "UPDATE TimeSheetEntity t SET t.timeIn =?1, t.attendanceDate =?2 WHERE t.employeeId = ?3";

			Query q = entityManager.createQuery(sql);
		
			q.setParameter(1, timeIn);
			q.setParameter(2, date);
			q.setParameter(3, employee.getEmployeeId());
			
			updateCount = q.executeUpdate();//returns no. of entries updated
				
		} catch (Exception e) {
			System.out.println("Error in employeeDAO: "+e.getMessage());
		}
		return updateCount;
	}
	
	@Override
	public Integer clockOut(Integer employeeId, String date, String timeOut) throws Exception {
		Integer updateCount = null;
		
		/*
		 * get user time card using date and id
		 * then add time out information
		 * post it back
		 */
		
		try {
			
			String sql = "UPDATE TimeSheetEntity t SET t.timeOut =?1 WHERE t.employeeId = ?2 AND t.attendanceDate =?3  ";
			Query q = entityManager.createQuery(sql);
		
			q.setParameter(1, timeOut);
			q.setParameter(2, employeeId);
			q.setParameter(3, date);
			
			updateCount = q.executeUpdate();//returns no. of entries updated
				
		} catch (Exception e) {
			System.out.println("Error in userLoginDAO: "+e.getMessage());
		}
		return updateCount;
	}



/* In the case you want to delete a timesheet entity, modify below code to acheive this
 * 	// deletes card of exiting customer
	@Override
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) {
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerId);
		for (Integer cardId : cardIdsToDelete) {
			CardEntity cardEntity = entityManager.find(CardEntity.class, cardId);
			customerEntity.getCardEntities().remove(cardEntity);
			entityManager.remove(cardEntity);
		}
	}
 */

}
