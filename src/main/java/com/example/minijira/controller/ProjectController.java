package com.example.minijira.controller;

import com.example.minijira.model.Project;
import com.example.minijira.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectRepository projectRepository;
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // GET API to fetch all projects
    @GetMapping("/projects")
    public List<Project> getAllProjects()  {
        return projectRepository.findAll();
    }

    // POST API to add new details
    @PostMapping("/projects")
    public String addProjects(@RequestBody Project project) {
        projectRepository.save(project);
        return "Data Inserted Successfully";
    }

    // DEL API to remove an entry by ID
    @DeleteMapping("/projects/{id}")
    public String deleteProjects(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "Data Deleted Successfully";
    }
}