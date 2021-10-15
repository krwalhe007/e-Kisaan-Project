package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Product;
import com.app.pojos.User;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
