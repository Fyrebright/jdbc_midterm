package com.example.jdbcdemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jdbcdemo.model.Project;

@SpringBootTest
public class ProjectServiceImplTest {
	
	@Autowired
	ProjectService service;
	
	@Test
	public void testGetAllProjects() {
		
		List<Project> list = service.getAll();
		
		assertEquals(2, list.size());
	}
	
	@Test
	public void testGetProject() {
		
		Project emp = service.getById(100l);
		
		assertEquals("test1", emp.getName());
	}
	
	@Test
	public void testCreateProject() {
		
		Project e = new Project(1l, "nv");
		
		service.create(e);
		
		Project e1 = service.getById(1l);
		
		assertEquals("nv", e1.getName());
		
        service.deleteById(1l);
	}
	
	@Test
	public void testUpdateProject() {
		
		Project emp = service.getById(100l);
        emp.setName("nv2_modified");
		
		service.update(emp);
		
		Project e1 = service.getById(100l);
		
		assertEquals("nv2_modified", e1.getName());
	}
	
	@Test
	public void testDeleteProject() {
		
		Project e = new Project(3l, "nv1");
		
		service.create(e);
		
		Project e1 = service.getById(3l);
		
		assertEquals("nv1", e1.getName());
		
		service.deleteById(3l);
		
		e1 = service.getById(3l);
		
		assertEquals(null, e1);
	}
}
