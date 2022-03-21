package com.genesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genesis.dao.AddressDAO;
import com.genesis.model.Address;

@Service(value="addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Override
	public String addAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * returnValue of 0 means nothing happened
		 * returnValue of 1 means succcess
		 * returnValue of -1 means failed
		 */
		Integer returnValue = 0;
		String result = "";
		try {
			returnValue = addressDAO.addAddress(address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Service.ADD_ADDRESS_EXCEPTION");
		}
		
		if(returnValue == 1) {
			result = "Address sucessfuly added";
		}
		if(returnValue == -1) {
			result = "Address not added";
		}
		if(returnValue == 0) {
			result = "Nothing happened";
		}
		
		return result;
	}

	@Override
	public Address getAddress(Integer addressId) throws Exception {
		// TODO Auto-generated method stub
		
		Address address;
		
		try {
			address = addressDAO.getAddress(addressId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Service.GET_ADDRESS_EXCEPTION");
		}
		
		return address;
	}

	@Override
	public Integer updateAddress(Integer addressId, Address address) throws Exception {
		// TODO Auto-generated method stub
		
		Integer returnValue = 0;
		
		try {
			returnValue = addressDAO.updateAddress(addressId, address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Service.UPDATE_ADDRESS_EXCEPTION");
		}
		
		return returnValue;
	}

	@Override
	public Integer deleteAddress(Integer addressId) throws Exception {
		// TODO Auto-generated method stub
		
		Integer returnValue = 0;
		
		try {
			returnValue = addressDAO.deleteAddress(addressId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Service.UPDATE_ADDRESS_EXCEPTION");
		}
		
		return returnValue;

	}

}
