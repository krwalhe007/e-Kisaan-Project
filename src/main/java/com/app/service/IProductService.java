package com.app.service;

import java.util.List;

import com.app.pojos.Product;

public interface IProductService {

	// add a meth to get all product list
	List<Product> getAllProducts();

	// add a method to delete product details
	String deleteProductDetails(long productId);

	// add a method to save product details(add product)
	Product addProductdetails(Product transientProduct);

	// add a method to fetch user details by id
	Product getProductDetailsId(long productId);

	// add a method to update existing product details
	Product updateProductDetails(Product detachedProduct);

}
