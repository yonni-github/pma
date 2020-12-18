package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.TimelineData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("")
	public String displayProjects(Model model) {
		List<Project> projects = proService.getAll();//get all projects from db
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("project", new Project());//binding object to view
		List<Employee> employees = empService.getAll();
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@GetMapping("/update")
	public String updateProjectForm(Model model, @RequestParam("id") long id) {
		Project pro =proService.findProjectById(id);
		model.addAttribute("project", pro);//binding object to view
		List<Employee> employees = empService.getAll();
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id) {
		proService.deleteProject(id);
		return "redirect:/projects";
	}
	
	@PostMapping("/save")
	public String createProject(@Valid Project project, Errors errors) { //@RequestParam List<Long> employees,
		if(errors.hasErrors()){
			return "projects/new-project";
		}
		proService.save(project);
		
//		//locate selected employees and set the project on the employees
//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//		
//		for(Employee emp: chosenEmployees) {
//			emp.setProjects(project);
//			empRepo.save(emp);
//		}
		return "redirect:/projects/new";//use redirect to prevent duplicate submissions
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {		
		List<TimelineData> projectTimelineData = proService.getProjectTimelines();
		//convert projectData to json data
		ObjectMapper om  = new ObjectMapper();
		String json = om.writeValueAsString(projectTimelineData);
		
		System.out.println("**********TIMELINES**************");
		System.out.println(json);
		model.addAttribute("projectTimeline", json);
		
		return "projects/project-timelines";
	}
}
