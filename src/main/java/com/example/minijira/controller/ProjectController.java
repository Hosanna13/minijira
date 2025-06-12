package com.example.minijira.controller;

import com.example.minijira.model.Project;
import com.example.minijira.repository.ProjectRepository;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectRepository projectRepository;
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // GET API to fetch all projects
    @GetMapping("/projects") // collections
    public List<Project> getAllProjects()  {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{id}") // resource
    public Project getProject(@PathVariable Long id){
        return projectRepository.findById(id).orElseThrow( () -> new RuntimeException("Project not found"));
    }

    // POST API to add new details
    @PostMapping("/projects")
    public String addProjects(@RequestBody Project project) {
        projectRepository.save(project);
        return "Data Inserted Successfully";
    }

    // PUT API to update exiisting project
    @PutMapping("/projects/{id}")
    public Project updateTitle(@PathVariable Long id, @RequestBody Project updatedProject){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        return projectRepository.save(project);
    }

    // PARTCH - Partial Update
    @PatchMapping("/projects/{id}")
    public Project patchProject(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (updates.containsKey("name")){
            project.setName((String) updates.get("name"));
        }
        if (updates.containsKey("description")){
            project.setDescription((String) updates.get("description"));
        }
        return projectRepository.save(project);

    }

    // DEL API to remove an entry by ID
    @DeleteMapping("/projects/{id}")
    public String deleteProjectById(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "Data Deleted Successfully";
    }

    // HEAD return headers
    @RequestMapping(value = "/projects", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headProjects(){
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headProjectsById(@PathVariable Long id){
        if (!projectRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // OPTINIONS (tells what methods are allowed)
    @RequestMapping(value = "/projects", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> opitionsProjects(){
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS, HttpMethod.HEAD).build();
    }
}