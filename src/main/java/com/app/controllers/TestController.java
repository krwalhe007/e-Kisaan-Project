package com.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "E-Kisaan Store";
	}
	
	@GetMapping("/user")
	@PreAuthorize( "hasRole('USER') or hasRole('CUSTOMER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('CUSTOMER')")
	public String moderatorAccess() {
		return "Customer Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board";
	}
}
