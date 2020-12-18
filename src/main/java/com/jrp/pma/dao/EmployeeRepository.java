package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

//N.B: PagingAndSortingRepository extends CrudRepository
@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployess") //override the default rest api end point of spring data rest i.e employees for this entity
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	@Override
	List<Employee> findAll();
	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e left join project_employee pe On pe.employee_id = e.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

	Employee findByEmail(String value); //possible b/c spring data/jpa can infer the implementation by just declaring the method properly
}

