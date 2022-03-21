package com.genesis.dao;

import com.genesis.model.Address;

public interface AddressDAO {
	public Integer addAddress(Address address) throws Exception;
	public Address getAddress(Integer employeeId) throws Exception;
	public Integer updateAddress(Integer employeeId, Address address) throws Exception;
	public Integer deleteAddress(Integer employeeId)throws Exception;
}
