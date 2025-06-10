/*
    Repository
        allows one to save new project or task objects
        fetch then (with findAll(), findByID(), etc.)
        delete or update them
* */
package com.example.minijira.repository;
import com.example.minijira.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}