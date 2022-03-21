package com.genesis.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Employee")
public class EmployeeEntity {
	
	@Id
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String designation;
	private Integer rfid;
	
	//private datatype fingerprint
	//private datatype facialrecog
	
	@Enumerated(value=EnumType.STRING)
	private UserGender userGender;
	
	private String regDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "login_id", unique = true)
	private UserLoginEntity userLoginEntity;
	
	/*
	 * Unidirectional @OneToMany with @JoinColumn
	 * The @JoinColumn annotation helps Hibernate to figure out that there is an employee_id Foreign Key column 
	 * in the Attendance table that defines this association.
	 * refer: https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private List<TimeSheetEntity> timeSheets; 
	private Long phoneNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", unique = true)
	private AddressEntity addressEntity;
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getRfid() {
		return rfid;
	}

	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}

	public UserGender getUserGender() {
		return userGender;
	}

	public void setUserGender(UserGender userGender) {
		this.userGender = userGender;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public List<TimeSheetEntity> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(List<TimeSheetEntity> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public UserLoginEntity getUserLogin() {
		return userLoginEntity;
	}

	public void setUserLogin(UserLoginEntity userLoginEntity) {
		this.userLoginEntity = userLoginEntity;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public AddressEntity getAddress() {
		return addressEntity;
	}
	
	public void setAddress(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

}
