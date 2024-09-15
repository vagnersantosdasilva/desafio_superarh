package com.superarh.tarefas_backend.service;

import com.superarh.tarefas_backend.model.ListTask;
import com.superarh.tarefas_backend.model.User;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskCreateDto;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.mapper.ListTask.ListTaskMapper;
import com.superarh.tarefas_backend.repository.ListTaskRepository;
import com.superarh.tarefas_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ListTaskService {

    private ListTaskRepository listTaskRepository;
    private UserRepository userRepository;

    @Autowired
    ListTaskService(ListTaskRepository listTaskRepository, UserRepository userRepository){
        this.listTaskRepository = listTaskRepository;
        this.userRepository = userRepository;
    }

    public ListTaskResponse create(ListTaskCreateDto listTaskCreateDto, Long idUser){
        Optional<User> userOptional =  userRepository.findById(idUser);
        if (userOptional.isPresent()){
            ListTask newListTask = new ListTask();
            newListTask.setName(listTaskCreateDto.name());
            newListTask.setDateCreate(LocalDateTime.now());
            newListTask.setWhenToDo(listTaskCreateDto.whenToDo());
            newListTask.setUser(userOptional.get());
            ListTask savedTask = listTaskRepository.save(newListTask);
            return  ListTaskMapper.INSTANCE.toDto(savedTask);
        }
        throw new RuntimeException("Usuário associado a lista de tarefas não foi encontrado!");
    }


}
