package com.jrp.pma.entities;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrp.pma.validators.UniqueValue;

@Entity
@Validated //in order NotNull, NotBlank, Size to work
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")
	@SequenceGenerator(name="employee_seq",sequenceName="employee_seq", allocationSize = 1)
	private long employeeId;
	
	@NotBlank(message="First Name Required.")
	@Size(min=2, max=50)
	private String firstName;
	
	@NotBlank(message="Last Name Required.")
	@Size(min=1, max=50)
	private String lastName;
	
	@Email
	@NotBlank(message="Must be valid email address.")
	@UniqueValue
	private String email;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
				fetch = FetchType.LAZY)
	@JoinTable(name="project_employee", 
				joinColumns=@JoinColumn(name="employee_id"),
				inverseJoinColumns=@JoinColumn(name="project_id")
	)
	
	@JsonIgnore //to stope cascading retrieval of objects resulting in infinite loop
	private List<Project> projects;
	

	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public Employee() {}
	public Employee(String fistName, String lastName, String email) {
		super();
		this.firstName = fistName;
		this.lastName = lastName;
		this.email = email;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
