package com.mydomain.springweb.employeehub.service;

import java.util.List;


import com.mydomain.springweb.employeehub.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(Long id);
	void deleteEmployeeById(Long id);
}
