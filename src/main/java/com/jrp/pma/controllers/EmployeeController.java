package com.jrp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("")// handles /employees route
	public String displayEmployees(Model model) {
		List<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());//binding object to view
		return "employees/new-employee";
	}
	
	@GetMapping("/update")
	public String updateEmployeeForm(Model model, @RequestParam("id") long id) {
		Employee emp =empService.findEmployeeById(id);
		model.addAttribute("employee", emp);//binding object to view
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long id) {
		empService.deleteEmployee(id);
		return "redirect:/employees";
	}
	
	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, Errors errors) {
		if(errors.hasErrors()){
			return "employees/new-employee";
		}
		empService.save(employee);
		return "redirect:/employees/new";
	}
}
