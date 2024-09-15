package com.superarh.tarefas_backend.controller;

import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskCreateDto;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.dto.user.UserCreateDto;
import com.superarh.tarefas_backend.model.dto.user.UserResponse;
import com.superarh.tarefas_backend.service.ListTaskService;
import com.superarh.tarefas_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/manager")
public class ListTaskController {

    private ListTaskService listTaskService;

    @Autowired
    ListTaskController(ListTaskService listTaskService){
        this.listTaskService = listTaskService;
    }

    @PostMapping("/user/{id}/listtask")
    public ResponseEntity<ListTaskResponse> createListTask(@RequestBody @Valid ListTaskCreateDto listTaskCreateDto, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){
        ListTaskResponse listTaskResponse = listTaskService.create(listTaskCreateDto, id);

        URI endereco = uriComponentsBuilder.path("/listTask/{id}").buildAndExpand(listTaskResponse.id()).toUri();

        return ResponseEntity.created(endereco).body(listTaskResponse);
    }

}
