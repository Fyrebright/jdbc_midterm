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
		ResultSet rs;
		List<Project> result = new ArrayList<Project>();
		try {
			Connection c = datasource.getConnection();
			PreparedStatement ps = c.prepareStatement(statement);
			rs = ps.executeQuery();
			rs.first();
			while(rs.next()) {
				result.add(new Project((long) rs.getInt(0), rs.getString(1)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Project getById(Long id) {
		String statement = "SELECT * FROM PROJECT WHERE id = " + id;
		ResultSet rs;
		Project result = new Project();
		try {
			Connection c = datasource.getConnection();
			PreparedStatement ps = c.prepareStatement(statement);
			rs = ps.executeQuery();
			rs.first();
			result = new Project((long) rs.getInt(0), rs.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void create(Project project) {
	    String statement = String.format("INSERT INTO PROJECT ( id, name ) VALUES ( %d, %s ) ", project.getId(), project.getName());
	    try {
			Connection c = datasource.getConnection();
			PreparedStatement ps = c.prepareStatement(statement);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Project project) {
		String statement = String.format("UPDATE PROJECT SET name = %s WHERE id = %d", project.getName(), project.getId());
		try {
			Connection c = datasource.getConnection();
			PreparedStatement ps = c.prepareStatement(statement);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(long l) {
		String statement = String.format("DELETE * FROM PROJECT WHERE id = %d", l);
		try {
			Connection c = datasource.getConnection();
			PreparedStatement ps = c.prepareStatement(statement);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
