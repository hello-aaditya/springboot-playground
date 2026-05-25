package com.mydomain.springweb.onetoonemapping.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user_profile")
public class UserProfile {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="phone_number")
	@NotBlank(message="Phone number is required")
	@Pattern(
			regexp="^[0-9]{10}$",
			message="Phone number must contain exactly 10 digits"
	)
	
	private String phoneNumber;
	
	@Column(name="address")
	@NotBlank(message="Address is required")
	@Size(min =5, max=150, message="Address must be between 5 and 150 characters")
	private String address;
	
	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	@NotNull(message="Gender is required")
	private Gender gender;
	
	@Column(name="dob")
	@NotNull(message="Date of Birth is required")
	@Past(message="Date of birth must be in the past")
	private LocalDate dob;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
	private User user;
	
	public UserProfile() {
		super();
	
	}

	public UserProfile(String phoneNumber, String address, Gender gender, LocalDate dob) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
	
}
