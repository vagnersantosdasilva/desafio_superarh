package com.superarh.tarefas_backend.controller;

import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskCreateDto;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskUpdateDto;
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

    @PutMapping("/user/{idUser}/listtask/{idListTask}")
    public ResponseEntity<ListTaskResponse> updateListTask(
            @RequestBody @Valid ListTaskUpdateDto listTaskUpdateDto,
            @PathVariable Long idUser,
            @PathVariable Long idListTaks){

        return ResponseEntity.ok(listTaskService.update(listTaskUpdateDto, idUser, idListTaks));
    }

    @GetMapping("/user/{idUser}/listtask")
    public ResponseEntity<?> getAllListTask(@PathVariable Long idUser){
        return ResponseEntity.ok(listTaskService.findAll(idUser));
    }


    @GetMapping("/user/{idUser}/listtask/{idListTask}")
    public ResponseEntity<?> getListTask(@PathVariable Long idUser, @PathVariable Long idListTask){
        return ResponseEntity.ok(listTaskService.findListTask(idUser,idListTask));
    }

    @DeleteMapping("/user/{idUser}/listtask/{idTaskList}")
    public ResponseEntity<?> deleteListTask(@PathVariable Long idUser, @PathVariable Long idTaskList){
        listTaskService.delete(idTaskList);
        return ResponseEntity.noContent().build();
    }

}
