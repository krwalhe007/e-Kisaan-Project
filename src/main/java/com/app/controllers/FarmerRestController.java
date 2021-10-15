package com.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.service.IProductService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/api/test/farmers")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmerRestController {
	// dependency: service layer i/f
	@Autowired
	private IProductService productService;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository productRepo;

	public FarmerRestController() {
		System.out.println("in cnstr of::" + getClass().getName());
	}

	// add rest api meth to fetch all products list
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		System.out.println("in getAllProducts()");
		return productService.getAllProducts();
	}

	// add rest api handling for deleting product by Id
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductDetails(@PathVariable long productId) {
		System.out.println("in deleteProductDetails()::" + productId);
		return ResponseEntity.ok(productService.deleteProductDetails(productId));
	}

	// add rest end point for getting product details(get id)
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDetailsId(@PathVariable long id) {
		System.out.println("in getProductDetailsId() for getting id::" + id);
		return ResponseEntity.ok(productService.getProductDetailsId(id));
	}

	// ad rest end point for updating user details
	@PutMapping("/{proId}/{userId}")
	public Product updateProductDetails(@PathVariable(value = "proId") long proId,
			@PathVariable(value = "userId") long userId, @Valid @RequestBody Product detachedUser) {
		System.out.println("in updateProductDetails()::" + detachedUser + "::" + proId);
		System.out.println("user id::" + userId);
		// chk if product exist by id
		Product existingProduct = productService.getProductDetailsId(proId);
		return userRepo.findById(userId).map(post -> {
			detachedUser.setSelectedUser(post);
			return productRepo.save(detachedUser);
		}).orElseThrow(() -> new ResourceNotFoundException("user id::" + userId + " not found"));
	}

	// rest api to add product detail
	@PostMapping("/add/{userId}")
	public Product addProductTemp(@PathVariable(value = "userId") long userId, @Valid @RequestBody Product product) {
		System.out.println("user id::" + userId);
		return userRepo.findById(userId).map(post -> {
			product.setSelectedUser(post);
			return productRepo.save(product);
		}).orElseThrow(() -> new ResourceNotFoundException("user id::" + userId + " not found"));
	}

}
