package com.danielgraciapalacios.taskflowapi.service;

import com.danielgraciapalacios.taskflowapi.entity.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> findAll();

    TaskList findById(long id);

    TaskList save(TaskList taskList);

    void deleteById(long id);
}
