package com.mydomain.springweb.employeehub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mydomain.springweb.employeehub.entity.Employee;
import com.mydomain.springweb.employeehub.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	// CONTRUCTOR DI
	private final EmployeeRepository employeeRepository;
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException("Employee not found for ID: " + id);
		}
		return employee;
	}
	
}
