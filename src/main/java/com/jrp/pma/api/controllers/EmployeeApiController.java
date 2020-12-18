package com.jrp.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@RestController
@RequestMapping(path="/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public Iterable<Employee> getEmployees(){
		
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id){
		return empRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody @Valid Employee employee) {
		
		return empRepo.save(employee);
	}
	
	//updates the whole record. ****Does a cascading delete on record!!!!
	@PutMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@RequestBody @Valid Employee employee) {
		
		return empRepo.save(employee);
	}
	
	//updates the record partially
	@PatchMapping(path="/{id}", consumes="application/json")
	
	public Employee partialUpdateEmployee(@PathVariable("id") Long id, @RequestBody @Valid Employee patchEmployee) {
		Employee emp = empRepo.findById(id).get();
		
		if(patchEmployee.getEmail() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		
		if(patchEmployee.getFirstName() != null) {
			emp.setFirstName(patchEmployee.getFirstName());
		}
		
		if(patchEmployee.getEmail() != null) {
			emp.setLastName(patchEmployee.getLastName());
		}
		
		return empRepo.save(emp);
	}
	
	//delete record
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		
		try {
			empRepo.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@GetMapping(params= {"page", "size"})
	public Iterable<Employee> getPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size){
		Pageable pageAndSize = PageRequest.of(page, size);
		return empRepo.findAll(pageAndSize);
		
	}

}
