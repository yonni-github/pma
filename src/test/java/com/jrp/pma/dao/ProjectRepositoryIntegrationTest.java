package com.jrp.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;

@SpringBootTest
@RunWith(SpringRunner.class)//for junit testing
@SqlGroup({@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:schema.sql", "classpath:data.sql"})}) //for sql scripts to run before or after test method. Not mandatory to run test.
public class ProjectRepositoryIntegrationTest {
	
	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject= new Project("new test project", "Complete", "Test project description");
		proRepo.save(newProject);
		assertEquals(5, proRepo.findAll().size());
	}
}
