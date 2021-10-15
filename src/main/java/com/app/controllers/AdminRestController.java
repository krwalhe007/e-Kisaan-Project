package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Address;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.repository.AddressRepository;
import com.app.service.IUserService;

@RestController
@RequestMapping("/api/test/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminRestController {
	// dependency: service layer i/f
	@Autowired
	private IUserService userService;
	@Autowired
	private AddressRepository addressRepo;

	public AdminRestController() {
		System.out.println("in cnstr of::" + getClass().getName());
	}

	// add rest api meth to fetch all Farmers list
	@GetMapping("/all")
	public List<User> getAllFarmers() {
		System.out.println("in getAllFarmers()");
		return userService.getAllFarmer();
	}

	// add a method to get address of users
	@GetMapping("/address/{userId}")
	public List<Address> getUserAddress(@PathVariable long userId) {
		User user = userService.getUserDetailsId(userId);
		return addressRepo.getUsersAddress(userId);
	}

	// add rest end point for getting farmer details(get id)
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserDetailsId(@PathVariable int id) {
		System.out.println("in getUserDetailsId() for getting id::" + id);
		return ResponseEntity.ok(userService.getUserDetailsId(id));
	}

	// add rest api handling for deleting farmer by Id
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserDetails(@PathVariable int userId) {
		System.out.println("in deleteUserDetails()::" + userId);
		return ResponseEntity.ok(userService.deleteUserDetails(userId));
	}

	@GetMapping("/specific/{userId}")
	List<Product> getProductsBySpecificUser(@PathVariable int userId) {
		System.out.println("in getProductsBySpecificUser()::" + userId);
		User existingUser = userService.getUserDetailsId(userId);
		return userService.getProductsBySpecificFarmer(userId);
	}

	// add a method to get user contact of product
	@GetMapping("/contact/{prodId}")
	List<User> getUserContact(@PathVariable long prodId) {
		System.out.println("prodId::" + prodId);
		return userService.getUserContact(prodId);
	}

}
