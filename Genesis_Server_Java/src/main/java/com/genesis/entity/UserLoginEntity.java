package com.genesis.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Profile")
public class UserLoginEntity {
	//@Id indicates the primary key
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="loginidgen",sequenceName="loginid_sequence",allocationSize=1)
	@GeneratedValue(generator="loginidgen",strategy=GenerationType.SEQUENCE)
	private Integer loginId;
	
	private Integer employeeId;
	private String userName;
	private String password;
	
	@Enumerated(value=EnumType.STRING)
	private Role role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public Integer getRfid() {
//		return employeeId;
//	}
//
//	public void setRfid(Integer employeeId) {
//		this.employeeId = employeeId;
//	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
}
