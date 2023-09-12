package com.slip.user.service;

import com.slip.user.Models.Tasks;

import java.util.List;

public interface TasksService {


    Tasks findTaskByID(Long id);

    String deleteTaskByID(Long id);

    List<Tasks> findAllTasksByUserId(String userRef);

    Tasks save(Tasks tasks);

    Tasks updateTaskBy(Tasks tasks);
}
