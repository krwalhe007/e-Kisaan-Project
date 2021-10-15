package com.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.pojos.ERole;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	// dependency: user repo i/p
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<User> getAllFarmer() {
		String jpql = "select u from User u join u.roles ur where ur.name=:role";
		return entityManager.createQuery(jpql, User.class)
				.setParameter("role", ERole.ROLE_USER).getResultList();

	}

	@Override
	public User getUserDetailsId(long userId) {

		return userRepo.findById(userId).orElseThrow(() -> new UserHandlingException("Invalid User Id"));
	}

	@Override
	public String deleteUserDetails(long userId) {
		userRepo.deleteById(userId);
		return "user deleted successfully";
	}

	@Override
	public List<Product> getProductsBySpecificFarmer(long userId) {

		return userRepo.getProductBySpecificFarmer(userId);
	}

	@Override
	public List<User> getUserContact(long prodId) {

		return userRepo.getContactByProductId(prodId);
	}

}
