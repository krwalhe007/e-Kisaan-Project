package com.app.service;

import java.util.List;

import com.app.pojos.Product;
import com.app.pojos.User;

public interface IUserService {

	// add a method to get all farmer details
	List<User> getAllFarmer();

	// add a method to fetch user details by id
	User getUserDetailsId(long userId);

	// add a method to delete product details
	String deleteUserDetails(long userId);

	// add a meth to fetch products details by partiular famer(id)
	List<Product> getProductsBySpecificFarmer(long userId);

	// add a method to get user contact
	List<User> getUserContact(long prodId);

}
