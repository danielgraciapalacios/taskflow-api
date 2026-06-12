package com.danielgraciapalacios.taskflowapi.repository;

import com.danielgraciapalacios.taskflowapi.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {}
