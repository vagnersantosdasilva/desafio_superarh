package com.superarh.tarefas_backend.controller;

import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.dto.Task.TaskCreateDto;
import com.superarh.tarefas_backend.model.dto.Task.TaskResponse;
import com.superarh.tarefas_backend.model.dto.Task.TaskUpdateDto;
import com.superarh.tarefas_backend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/manager")
public class TaskController {

    private TaskService taskService;

    @Autowired
    TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid TaskCreateDto taskCreateDto, UriComponentsBuilder uriComponentsBuilder){
        TaskResponse taskResponse = taskService.createTask(taskCreateDto);

        URI endereco = uriComponentsBuilder.path("/task/{id}").buildAndExpand(taskResponse.id()).toUri();

        return ResponseEntity.created(endereco).body(taskResponse);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody @Valid TaskUpdateDto taskUpdateDto, @PathVariable Long id){
        TaskResponse taskResponse = taskService.updateTask(taskUpdateDto, id);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasklist/{id}/task")
    public ResponseEntity<?> findAllTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.findAll(id));
    }

    @GetMapping("/task/{idTask}")
    public ResponseEntity<TaskResponse> findTask(@PathVariable Long idTask){
        return ResponseEntity.ok(taskService.findTask(idTask));
    }

    @PutMapping("/task/{idTask}/start")
    public ResponseEntity<TaskResponse> startTask(@PathVariable Long idTask){
        return ResponseEntity.ok(taskService.startTask(idTask));
    }

    @PutMapping("/task/{idTask}/finish")
    public ResponseEntity<TaskResponse> finishTask(@PathVariable Long idTask){
        return ResponseEntity.ok(taskService.finishTask(idTask));
    }

}
