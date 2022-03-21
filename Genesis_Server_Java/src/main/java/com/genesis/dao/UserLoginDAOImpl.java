package com.genesis.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.genesis.entity.UserLoginEntity;
import com.genesis.model.UserLogin;

@Repository(value="userLoginDAO")
public class UserLoginDAOImpl implements UserLoginDAO {
	
	@PersistenceContext
	private EntityManager entityManager;



	@Override
	public Integer addUserLogin(UserLogin userLogin) throws Exception {
		//This checks if the userName exists in the db in login table
		//if to doesnt exsit, it adds the details, else it rejects the details
		Integer retValue = 0;
		
		try {			
			UserLoginEntity userLoginEntity = new UserLoginEntity();
			//userLoginEntity.setRfid(userLogin.getRfid());
			userLoginEntity.setUserName(userLogin.getUserName());
			userLoginEntity.setPassword(userLogin.getPassword());
			userLoginEntity.setRole(userLogin.getRole());
			
			//System.out.println(userLoginEntity.getRfid());
			System.out.println(userLoginEntity.getUserName());
			System.out.println(userLoginEntity.getPassword());
			System.out.println(userLoginEntity.getRole());
			
			entityManager.persist(userLoginEntity);
			//retValue = userLoginEntity.getUserName();
			retValue = 1;
			
			
		} catch (Exception e) {
			System.out.println("Error in userLoginDAO: "+e.getMessage());
		}
		return retValue;
	}



	@Override
	public UserLogin getUserCredentials(String userName) throws Exception {
		/*
		 * This method returns the user whose username is given.
		 * This has to be done using the sql query as the entity manager's find
		 * method works with only Integers
		 */
		UserLogin userLogin = null;
		
		String sql = "SELECT l FROM UserLoginEntity l WHERE l.userName = ?1";
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, userName);
		
		try {
			UserLoginEntity userLoginEntity = (UserLoginEntity) q.getSingleResult();
				
			if(userLoginEntity != null) {
				userLogin = new UserLogin();
				userLogin.setLoginId(userLoginEntity.getLoginId());
				userLogin.setEmployeeId(userLoginEntity.getEmployeeId());
				userLogin.setPassword(userLoginEntity.getPassword());
				userLogin.setUserName(userLoginEntity.getUserName());
				userLogin.setRole(userLoginEntity.getRole());
			}
		} catch (Exception NoResultException) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			throw new Exception("Dao.USER_DOES_NOT_EXIST");
		}
		System.out.println(userLogin.getLoginId());
		System.out.println(userLogin.getEmployeeId());
		return userLogin;
	}



	@Override
	public Integer deleteUserCredentials(Integer rfid) throws Exception {
		try {
			UserLoginEntity userLoginEntity = entityManager.find(UserLoginEntity.class, rfid);
			//change rfid to loginId
			
//			if(userLoginEntity.getRfid() == rfid) {
//				entityManager.remove(userLoginEntity);
//				//userId=userEntity.getUserId();
//				//if implemented, then the user exists
//			}
//			else {
//				rfid = -1;
//				//User does not Exists
//			}
//			
		} catch (NullPointerException e) {
			//if there is a NullPointerException the the user does not exists
			rfid = 0;
		}
		
		return rfid;
	}



	@Override
	public List<UserLogin> getAllUserCredentials() throws Exception {
		String sql = "SELECT u FROM UserLoginEntity u";
		Query q = entityManager.createQuery(sql);
		
		List<UserLoginEntity> userLoginList = q.getResultList();
		List<UserLogin> finalList = new ArrayList<>();
		
		for(UserLoginEntity userLoginEntity:userLoginList) {
						
			UserLogin userLogin = new UserLogin();
			
			//System.out.println(customer.getDvd().getDvdType().equals(dvdType));
			//System.out.println(customer.getDvd().getDvdType()+" "+dvdType);
			
			//userLogin.setRfid(userLoginEntity.getRfid());
			userLogin.setEmployeeId(userLoginEntity.getEmployeeId());
			userLogin.setUserName(userLoginEntity.getUserName());
			userLogin.setPassword(userLoginEntity.getPassword());
			userLogin.setRole(userLoginEntity.getRole());
			
			
			finalList.add(userLogin);
		}
		
		return finalList;
	}


}
