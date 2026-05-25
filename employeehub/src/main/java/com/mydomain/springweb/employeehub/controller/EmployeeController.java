package com.mydomain.springweb.employeehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mydomain.springweb.employeehub.service.EmployeeService;

@Controller
public class EmployeeController {

	// CONSTRUCTOR DI
	private final EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// DISPLAY LIST OF EMPLOYEES
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		model.addAttribute("listEmployee", employeeService.getAllEmployees());
		return "index";
	}
}
