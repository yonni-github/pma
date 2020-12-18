package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.TimelineData;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> getAll(){
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}
	
	public List<TimelineData> getProjectTimelines(){
		return proRepo.getProjectTimelines();
	}

	public Project findProjectById(long id) {
		
		return proRepo.findById(id).get();
	}

	public void deleteProject(long id) {
		proRepo.deleteById(id);
		
	}

}
