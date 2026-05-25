package com.mydomain.springweb.onetoonemapping;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mydomain.springweb.onetoonemapping.entity.Gender;
import com.mydomain.springweb.onetoonemapping.entity.User;
import com.mydomain.springweb.onetoonemapping.entity.UserProfile;
import com.mydomain.springweb.onetoonemapping.repository.UserRepository;


@SpringBootApplication
public class SpringbootHibernateOneToOneMappingApplication implements CommandLineRunner{

	// CONSTRUCTOR DI
	private final UserRepository userRepository;
	
	public SpringbootHibernateOneToOneMappingApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOneToOneMappingApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		// USER OBJECT
		User user = new User();
		user.setName("Alpha");
		user.setEmail("misteralpha@email.com");
		
		// USER PROFILE OBJECT
		UserProfile userProfile = new UserProfile();
		userProfile.setAddress("AlphaCity");
		userProfile.setDob(LocalDate.of(1999, 05, 25));
		userProfile.setGender(Gender.MALE);
		userProfile.setPhoneNumber("1234567890");
		
		user.setUserProfile(userProfile);
		userProfile.setUser(user);
		
		userRepository.save(user);
		
	}

}
