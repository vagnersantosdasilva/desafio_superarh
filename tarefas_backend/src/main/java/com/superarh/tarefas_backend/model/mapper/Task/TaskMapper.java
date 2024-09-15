package com.superarh.tarefas_backend.model.mapper.Task;

import com.superarh.tarefas_backend.model.Task;

import com.superarh.tarefas_backend.model.dto.Task.TaskCreateDto;
import com.superarh.tarefas_backend.model.dto.Task.TaskResponse;
import com.superarh.tarefas_backend.model.dto.Task.TaskUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    Task toEntity(TaskCreateDto taskCreateDto);
    Task toUpdateEntity (TaskUpdateDto taskUpdateDto);
    TaskResponse toDto(Task task);
}
