package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Validated //in order NotNull, NotBlank, Size to work
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_seq")
	@SequenceGenerator(name="project_seq",sequenceName="project_seq", allocationSize = 1)
	private long projectId;
	
	@NotBlank(message="Project Name Required.")
	@Size(min=2, max=50)
	private String name;
	
	@NotBlank(message="Stage Status Required.")
	private String stage;//NOTESTARTED, COMPLETED, INPROGRESS
	
	@NotBlank(message="Description Required.")
	@Size(min=2, max=500)
	private String description;
	
	@NotNull(message="Start Date Required.")
	private Date startDate;
	
	@NotNull(message="End Date Required.")
	private Date endDate;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", 
				joinColumns=@JoinColumn(name="project_id"),
				inverseJoinColumns=@JoinColumn(name="employee_id")
	)
	
	@JsonIgnore //to stop cascading retrieval of objects resulting in infinite loop
	private List<Employee> employees;
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Project() {}
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	public long getId() {
		return projectId;
	}
	public void setId(long id) {
		this.projectId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addEmployee(Employee emp1) {
		if(this.employees == null)
			employees = new ArrayList<Employee>();
		this.employees.add(emp1);
		
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
