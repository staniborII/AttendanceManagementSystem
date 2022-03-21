package com.genesis.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.genesis.entity.AddressEntity;
import com.genesis.model.Address;

@Repository(value = "addressDAO")
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Integer addAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		Integer retValue = 0;

		try {			
			AddressEntity addressEntity = new AddressEntity();
			//userLoginEntity.setRfid(userLogin.getRfid());
			addressEntity.setCity(address.getCity());
			addressEntity.setState(address.getState());
			addressEntity.setStreetName(address.getStreetName());
			addressEntity.setStreetNo(address.getStreetNo());
			addressEntity.setZip(address.getZip());
			
			entityManager.persist(addressEntity);
			//retValue = userLoginEntity.getUserName();
			retValue = 1;
			
			
		} catch (Exception e) {
			System.out.println("Error in addressDAO: "+e.getMessage());
		}
		return retValue;
	}

	@Override
	public Address getAddress(Integer employeeId) throws Exception {
		// TODO Auto-generated method stub
		Address address = null;
		
		String sql = "SELECT l FROM AddressEntity l WHERE l.employeeId = ?1";
		Query q = entityManager.createQuery(sql);
		q.setParameter(1, employeeId);
		
		try {
			AddressEntity addressEntity = (AddressEntity) q.getSingleResult();
				
			if(addressEntity != null) {
				address = new Address();
				address.setCity(addressEntity.getCity());
				address.setState(addressEntity.getState());
				address.setStreetName(addressEntity.getStreetName());
				address.setStreetNo(addressEntity.getStreetNo());
				address.setZip(addressEntity.getZip());
			}
		} catch (Exception NoResultException) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			throw new Exception("Dao.USER_DOES_NOT_HAVE_AN_ADDRESS");
		}
		System.out.println(address.getAddressId());
		System.out.println(address.getEmployeeId());
		return address;
	}

	@Override
	public Integer updateAddress(Integer addressId, Address address) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAddress(Integer addressId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
