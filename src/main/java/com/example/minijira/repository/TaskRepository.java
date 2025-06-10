package com.example.minijira.repository;
import com.example.minijira.model.Task;
import com.example.minijira.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByProject(Project project);
}
