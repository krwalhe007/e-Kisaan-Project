package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ProductHandlingException;
import com.app.pojos.Product;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{
	
	//dependency::dao layer i/f
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public String deleteProductDetails(long productId) {
		productRepo.deleteById(productId);
		return "product details deleted";
	}

	@Override
	public Product addProductdetails(Product transientProduct) {
		// TODO Auto-generated method stub
		return productRepo.save(transientProduct);
	}

	@Override
	public Product getProductDetailsId(long productId) {
		return productRepo.findById(productId).orElseThrow(() -> new ProductHandlingException("Invalid Product Id"));
	}

	@Override
	public Product updateProductDetails(Product detachedProduct) {
		// TODO Auto-generated method stub
		return productRepo.save(detachedProduct);
	}	
	
}
