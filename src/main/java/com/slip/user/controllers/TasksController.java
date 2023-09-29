package com.slip.user.controllers;

import com.slip.user.Models.Tasks;
import com.slip.user.service.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/tasks")
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping()
    public Map<String, List<Tasks>> getTasksByUserID(@RequestParam String UserId){
        return Map.of("tasks",tasksService.findAllTasksByUserId(UserId));
    }
    @GetMapping("/{id}")
    public Tasks getTasksID(@PathVariable String id){
        return tasksService.findTaskByID(Long.valueOf(id));
    }
    @DeleteMapping("/{taskId}")
    public String deleteTasksID(@PathVariable String taskId){
        return tasksService.deleteTaskByID(Long.valueOf(taskId));
    }
    @PutMapping()
    public Tasks updateTasks(@PathVariable Tasks tasks){
        return tasksService.updateTaskBy(tasks);
    }
    @PostMapping()
    public ResponseEntity<Tasks> addTasksForUser(@RequestParam String UserId , @RequestBody Tasks tasks){
        tasks.setUserRef(UserId);
        return ResponseEntity.ok(tasksService.save(tasks));
    }
}
