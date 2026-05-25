package com.mydomain.springweb.compositeprimarykey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee {

	@EmbeddedId
	private EmployeeIdentity id;
	
	@Column(name="name")
	@NotBlank(message="Name is required")
	@Size(min=2, message="Name must have atleast 2 characters")
	private String name;
	
	@Column(name="email")
	@NotBlank(message = "Email is required")
	private String email;
	
	@Column(name="phone_number")
	@NotBlank(message="Phone Number is required")
	@Pattern(regexp = "^[0-9]{10}$", message="Phone number must contain exactly 10 digits")
	private String phoneNumber;

	public Employee() {
		super();
	}

	public Employee(EmployeeIdentity id, String name, String email, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public EmployeeIdentity getId() {
		return id;
	}

	public void setId(EmployeeIdentity id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
	
	
	
}
