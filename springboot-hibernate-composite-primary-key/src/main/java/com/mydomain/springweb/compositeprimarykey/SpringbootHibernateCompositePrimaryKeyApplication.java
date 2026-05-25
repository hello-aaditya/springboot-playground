package com.mydomain.springweb.compositeprimarykey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mydomain.springweb.compositeprimarykey.entity.Employee;
import com.mydomain.springweb.compositeprimarykey.entity.EmployeeIdentity;
import com.mydomain.springweb.compositeprimarykey.repository.EmployeeRepository;

@SpringBootApplication
public class SpringbootHibernateCompositePrimaryKeyApplication implements CommandLineRunner {

	// CONSTRUCTOR DI
	private final EmployeeRepository employeeRepository;
	public SpringbootHibernateCompositePrimaryKeyApplication(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateCompositePrimaryKeyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		employeeRepository.deleteAllInBatch();
		
		// EMPLOYEE OBJECT AND INSERT IN DB
		Employee employee = new Employee(new EmployeeIdentity("E-101", "D-1"), "Alpha", "alpha@email.com", "1234567890");
		employeeRepository.save(employee);
		
		employeeRepository.findById(new EmployeeIdentity("E-101", "D-1"));
	}

}
