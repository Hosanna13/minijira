package com.example.minijira.controller;

import com.example.minijira.model.Project;
import com.example.minijira.model.Task;
import com.example.minijira.repository.ProjectRepository;
import com.example.minijira.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskRepository taskRepository;
    private ProjectRepository projectRepository;

    public TaskController(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    // GET API to fetch tasks by project
    @GetMapping("/projects/{projectId}/tasks")
    List<Task> findAllTasks(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return taskRepository.findByProject(project);
    }

    // POST APi to add a new Task
    @PostMapping("/projects/{projectId}/tasks")
    public Task addTask(@PathVariable Long projectId, @RequestBody Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);
        return taskRepository.save(task);
    }

    // PUT API to update existing task
    @PutMapping("tasks/{taskId}")
    public Task getTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.SetDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }

    // DELETE as task by ID
    @DeleteMapping("tasks/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
        return "Task Deleted";
    }
}