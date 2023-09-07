package com.slip.user.service;

import com.slip.user.Models.Tasks;
import com.slip.user.Models.User;

import java.util.List;

public interface TasksService {


    Tasks findTaskByID(Long id);

    List<Tasks> findAllTasksByUserId(String userRef);

    Tasks save(Tasks tasks);
}
