package com.mydomain.springweb.employeehub.service;

import java.util.List;

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
	
}
