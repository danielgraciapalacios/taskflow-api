package com.danielgraciapalacios.taskflowapi.controller;

import com.danielgraciapalacios.taskflowapi.dto.TaskListRequest;
import com.danielgraciapalacios.taskflowapi.dto.TaskListResponse;
import com.danielgraciapalacios.taskflowapi.entity.TaskList;
import com.danielgraciapalacios.taskflowapi.service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public List<TaskListResponse> findAll() {
        return taskListService.findAll().stream()
                .map(TaskListResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskListResponse findById(@PathVariable long id) {
        return TaskListResponse.from(taskListService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskListResponse> create(@Valid @RequestBody TaskListRequest request,
                                                   UriComponentsBuilder uriBuilder) {
        TaskList saved = taskListService.save(toEntity(request));

        URI location = uriBuilder.path("/api/task-lists/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(TaskListResponse.from(saved));
    }

    @PutMapping("/{id}")
    public TaskListResponse update(@PathVariable long id,
                                   @Valid @RequestBody TaskListRequest request) {
        TaskList taskList = taskListService.findById(id);
        taskList.setName(request.name());
        taskList.setDescription(request.description());
        return TaskListResponse.from(taskListService.save(taskList));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        taskListService.deleteById(id);
    }

    private TaskList toEntity(TaskListRequest request) {
        return new TaskList(request.name(), request.description());
    }
}
