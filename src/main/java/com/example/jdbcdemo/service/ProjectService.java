package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.model.Project;

public interface ProjectService {

	void create(Project model);

	Project getById(Long id);

	List<Project> getAll();

	void update(Project e);

	void deleteById(long l);

}
