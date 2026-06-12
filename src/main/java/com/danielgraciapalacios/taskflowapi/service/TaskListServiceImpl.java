package com.danielgraciapalacios.taskflowapi.service;

import com.danielgraciapalacios.taskflowapi.entity.TaskList;
import com.danielgraciapalacios.taskflowapi.repository.TaskListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList findById(long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> new TaskListNotFoundException(id));
    }

    @Override
    @Transactional
    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (!taskListRepository.existsById(id)) {
            throw new TaskListNotFoundException(id);
        }
        taskListRepository.deleteById(id);
    }
}
