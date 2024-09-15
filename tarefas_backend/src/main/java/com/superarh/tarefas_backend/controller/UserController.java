package com.superarh.tarefas_backend.controller;

import com.superarh.tarefas_backend.model.dto.user.UserCreateDto;
import com.superarh.tarefas_backend.model.dto.user.UserResponse;
import com.superarh.tarefas_backend.model.dto.user.UserUpdateDto;
import com.superarh.tarefas_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/manager")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateDto createUserDto, UriComponentsBuilder uriComponentsBuilder){
        UserResponse userResponse = userService.create(createUserDto);

        URI endereco = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userResponse.id()).toUri();

        return ResponseEntity.created(endereco).body(userResponse);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto,
                                                   @PathVariable Long id){
        UserResponse userResponse = userService.update(userUpdateDto, id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id){
        UserResponse userResponse = userService.findById(id);
        return ResponseEntity.ok(userResponse);
    }
}
