package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.jdbcdemo.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {
	private JdbcTemplate jdbcTemplate;

	protected DataSource datasource;

	public ProjectServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		datasource = this.jdbcTemplate.getDataSource();
	}
	
	@Override
	public List<Project> getAll() {
    String statement = "SELECT * FROM PROJECT";
		this.jdbcTemplate.update(statement);
		
		return null;
	}
	
	@Override
	public Project getById(Long id) {
		String statement = "SELECT * FROM PROJECT WHERE id = " + id;
		this.jdbcTemplate.update(statement);
		
		return null;
	}

	@Override
	public void create(Project project) {
    String statement = String.format("INSERT INTO PROJECT (id, name) VALUES (%d, %s)", project.getId(), project.getName());
		this.jdbcTemplate.update(statement);
	}

	@Override
	public void update(Project project) {
		String statement = String.format("UPDATE PROJECT SET name = %s WHERE id = %d", project.getId(), project.getName());
		this.jdbcTemplate.update(statement);

	}

	@Override
	public void deleteById(long l) {
		String statement = String.format("DELETE * FROM PROJECT WHERE id = %d", project.getId());
		this.jdbcTemplate.update(statement);

	}

}
