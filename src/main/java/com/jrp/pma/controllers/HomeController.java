package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;

	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		model.addAttribute("version", ver);
		
		List<Project> projects = proService.getAll();//get all projects from db
		model.addAttribute("projects", projects);
		
		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
		model.addAttribute("employeesProjectCnt", employeesProjectCnt);
		
		List<ChartData> projectData = proService.getProjectStatus();
		Map<String, Object> map = new HashMap<>();
		//convert projectData to json data
		ObjectMapper om  = new ObjectMapper();
		String json = om.writeValueAsString(projectData);
		model.addAttribute("projectStatus", json);
		
		return "main/home";
	}
}
