package com.superarh.tarefas_backend.model.mapper.Task;

import com.superarh.tarefas_backend.model.Task;

import com.superarh.tarefas_backend.model.dto.Task.TaskCreateDto;
import com.superarh.tarefas_backend.model.dto.Task.TaskResponse;
import com.superarh.tarefas_backend.model.dto.Task.TaskUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "listTaskId", target = "listTask.id")
    Task toEntity(TaskCreateDto taskCreateDto);

    @Mapping(source = "listTaskId", target = "listTask.id")
    Task toUpdateEntity (TaskUpdateDto taskUpdateDto);

    @Mapping(source = "listTask.id", target = "listTaskId")
    TaskResponse toDto(Task task);




}
