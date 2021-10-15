package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Product;
import com.app.pojos.Role;
import com.app.pojos.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("select p from Product p where p.selectedUser.id = :userId")
	List<Product> getProductBySpecificFarmer(@Param("userId") long userId);

	@Query("select u from User u join u.products p where p.id = :prodId")
	List<User> getContactByProductId(@Param("prodId") long prodId);

}
