package com.slip.user.service.impl;

import com.slip.user.Models.Tasks;
import com.slip.user.repositories.TasksRepostitory;
import com.slip.user.service.TasksService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TasksServiceImpl implements TasksService {
    private  final TasksRepostitory tasksRepostitory;

    public TasksServiceImpl(TasksRepostitory tasksRepostitory) {
        this.tasksRepostitory = tasksRepostitory;
    }

    @Override
    public Tasks findTaskByID(Long id){
        return tasksRepostitory.findById(id).orElseThrow();
    }

    @Override
    public String deleteTaskByID(Long id){
         tasksRepostitory.deleteById(id);
         return "Task deleted successfully";
    }
    @Override
    public Tasks updateTaskBy(Tasks tasks){
        tasks.setUpdatedAt(Instant.now());
        return tasksRepostitory.save(tasks);
    };

    @Override
    public List<Tasks> findAllTasksByUserId(String userRef){
        return tasksRepostitory.getUserByUserId(userRef);
    }
    @Override
    public Tasks save(Tasks tasks){
        tasks.setCreatedAt(Instant.now());
        tasks.setUpdatedAt(Instant.now());
        return tasksRepostitory.save(tasks);
    }
}
