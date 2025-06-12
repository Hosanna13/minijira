package com.example.minijira.model;

import com.example.minijira.repository.ProjectRepository;
import com.example.minijira.repository.TaskRepository;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project_id")
    private Project project;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Project getProject() {
        return project;
    }

    public Long getId() {
        return id;
    }

    public void setProject(Project project1) {
        this.project = project1;
    }

    public void setTitle(String title1) {
        this.title = title1;
    }

    public void SetDescription(String description1){
        this.description = description1;
    }

    public void setStatus (Status status1){
        this.status = status1;
    }

    public void setId(Long id1) {
        this.id = id1;
    }



}