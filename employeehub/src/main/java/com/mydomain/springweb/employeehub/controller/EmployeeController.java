package com.mydomain.springweb.employeehub.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mydomain.springweb.employeehub.entity.Employee;
import com.mydomain.springweb.employeehub.service.EmployeeService;

import jakarta.validation.Valid;

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
		
		return findPaginated(1, "firstName", "asc", model);
	}
	
	// CREATE MODEL ATTRIBUTE TO BIND FORM DATA 
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	// SAVE EMPLOYEE TO DB
	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "new_employee";
		}
		
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	// GET EMPLOYEE FROM THE SERVICE
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		
		//SET EMPLOYEE AS A MODEL ATTRIBUTE TO PRE-POPULATE THE FORM
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	// CALL DELETE EMPLOYEE METHOD 
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value="id") Long id) {
		
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	// 
	@GetMapping("/page/{pageNo}")
	public String findPaginated(
			@PathVariable(value = "pageNo") int pageNo,

			@RequestParam(defaultValue = "firstName")
			String sortField,

			@RequestParam(defaultValue = "asc")
			String sortDir,

			Model model) {
		int pageSize = 5;
		
		Page<Employee> page =  employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		
		return "index";
	}
}
