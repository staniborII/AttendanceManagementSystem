package com.genesis.service;


import com.genesis.model.Address;

public interface AddressService {
	public String addAddress(Address address) throws Exception;
	public Address getAddress(Integer addressId) throws Exception;
	public Integer updateAddress(Integer addressId, Address address) throws Exception;
	public Integer deleteAddress(Integer addressId)throws Exception;
}
