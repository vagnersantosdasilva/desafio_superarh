package com.superarh.tarefas_backend.service;

import com.superarh.tarefas_backend.exception.ResourceNotFound;
import com.superarh.tarefas_backend.model.ListTask;
import com.superarh.tarefas_backend.model.User;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskCreateDto;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskUpdateDto;
import com.superarh.tarefas_backend.model.mapper.ListTask.ListTaskMapper;
import com.superarh.tarefas_backend.repository.ListTaskRepository;
import com.superarh.tarefas_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        throw new ResourceNotFound("Usuário associado a lista de tarefas não foi encontrado!");
    }


    public ListTaskResponse update(ListTaskUpdateDto listTaskUpdateDto, Long idUser, Long idListTaks) {
        Optional<User> userOptional =  userRepository.findById(idUser);
        Optional<ListTask> listTaskOptional = listTaskRepository.findById(idListTaks);
        if (userOptional.isPresent()){
            if (listTaskOptional.isPresent()){
                ListTask updateListTask = new ListTask();
                updateListTask.setName(listTaskUpdateDto.name());
                updateListTask.setWhenToDo(listTaskUpdateDto.whenToDo());
                updateListTask.setUser(userOptional.get());
                ListTask savedTask = listTaskRepository.save(updateListTask);
                return  ListTaskMapper.INSTANCE.toDto(savedTask);
            }
            throw new ResourceNotFound("ListTask não encontrado!");
        }
        throw new ResourceNotFound("Usuário associado a lista de tarefas não foi encontrado!");
    }

    public List<ListTaskResponse> findAll(Long idUser) {
        return listTaskRepository.findByUserId(idUser)
                .stream()
                .map(ListTaskMapper.INSTANCE::toDto)
                .toList();

    }

    public void delete(Long idListTask){
        Optional<ListTask> listTaskOptional = listTaskRepository.findById(idListTask);
        if (listTaskOptional.isEmpty()){
            throw new RuntimeException("ListTask não encontrado!");
        }
        listTaskRepository.delete(listTaskOptional.get());
    }

    public ListTaskResponse findListTask(Long idUser, Long idListTask) {
        Optional<ListTask> listTaskOptional = listTaskRepository.findById(idListTask);
        if (listTaskOptional.isPresent()) {
            return ListTaskMapper.INSTANCE.toDto(listTaskOptional.get());
        }
        throw new ResourceNotFound("ListTask não encontrado!");
    }
}
