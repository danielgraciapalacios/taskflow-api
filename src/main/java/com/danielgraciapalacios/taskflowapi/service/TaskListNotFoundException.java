package com.danielgraciapalacios.taskflowapi.service;

public class TaskListNotFoundException extends RuntimeException {
    public TaskListNotFoundException(long id) {
        super("Task list %d not found".formatted(id));
    }
}