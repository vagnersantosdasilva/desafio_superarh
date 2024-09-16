package com.superarh.tarefas_backend.service;

import com.superarh.tarefas_backend.exception.ResourceNotFound;
import com.superarh.tarefas_backend.model.ListTask;
import com.superarh.tarefas_backend.model.StatusTask;
import com.superarh.tarefas_backend.model.Task;
import com.superarh.tarefas_backend.model.dto.Task.TaskCreateDto;
import com.superarh.tarefas_backend.model.dto.Task.TaskResponse;
import com.superarh.tarefas_backend.model.dto.Task.TaskUpdateDto;
import com.superarh.tarefas_backend.model.mapper.Task.TaskMapper;
import com.superarh.tarefas_backend.repository.ListTaskRepository;
import com.superarh.tarefas_backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private ListTaskRepository listTaskRepository;

    @Autowired
    TaskService(TaskRepository taskRepository, ListTaskRepository listTaskRepository){
        this.taskRepository = taskRepository;
        this.listTaskRepository = listTaskRepository;
    }

    public TaskResponse createTask(TaskCreateDto taskCreateDto){
        Optional<ListTask> listTaskOptional = listTaskRepository.findById(taskCreateDto.listTaskId());
        if (listTaskOptional.isPresent()){
            Task newTask = TaskMapper.INSTANCE.toEntity(taskCreateDto);
            newTask.setStatus(StatusTask.PENDENTE);
            return TaskMapper.INSTANCE.toDto(taskRepository.save(newTask));
        }
        throw new ResourceNotFound("ListTask associado ao task não foi encontrado");
    }


    public TaskResponse updateTask(TaskUpdateDto taskUpdateDto, Long id){

        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new ResourceNotFound("Task com ID " + id + " não foi encontrado");
        }

        Optional<ListTask> listTaskOptional = listTaskRepository.findById(taskUpdateDto.listTaskId());
        if (listTaskOptional.isEmpty()) {
            throw new ResourceNotFound("ListTask com ID " + taskUpdateDto.listTaskId() + " não foi encontrado");
        }

        Task updateTask = TaskMapper.INSTANCE.toUpdateEntity(taskUpdateDto);
        return TaskMapper.INSTANCE.toDto(taskRepository.save(updateTask));

    }

    public void deleteTask(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()){
            throw new ResourceNotFound("Task com ID "+id+" não foi encontrado");
        }
        taskRepository.delete(taskOptional.get());
    }

    public List<TaskResponse> findAll(Long idListTask){
        return taskRepository.findByListTaskId(idListTask)
                .stream()
                .map(TaskMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public TaskResponse startTask(Long idTask){
        Optional<Task> taskOptional = taskRepository.findById(idTask);
        if (taskOptional.isEmpty()){
            throw new ResourceNotFound("Task não localizada!");
        }
        Task updateTask = taskOptional.get();
        updateTask.setStatus(StatusTask.EXECUTANDO);
        updateTask.setDateStart(LocalDateTime.now());
        return TaskMapper.INSTANCE.toDto(taskRepository.save(updateTask));
    }

    public TaskResponse finishTask(Long idTask){
        Optional<Task> taskOptional = taskRepository.findById(idTask);
        if (taskOptional.isEmpty()){
            throw new ResourceNotFound("Task não localizada!");
        }
        Task updateTask = taskOptional.get();
        updateTask.setStatus(StatusTask.CONCLUIDO);
        updateTask.setDateFinish(LocalDateTime.now());
        return TaskMapper.INSTANCE.toDto(taskRepository.save(updateTask));
    }

    public TaskResponse findTask(Long idTask) {
        Optional<Task> taskOptional = taskRepository.findById(idTask);
        if (taskOptional.isEmpty()){
          throw new ResourceNotFound("Task não encontrado");
        }
        return TaskMapper.INSTANCE.toDto(taskOptional.get());
    }
}
