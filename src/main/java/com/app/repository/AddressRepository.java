package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("select a from Address a where a.user.id=:userId")
	List<Address> getUsersAddress(@Param("userId") long userId);
}
