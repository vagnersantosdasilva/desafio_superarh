package com.superarh.tarefas_backend.model.mapper.ListTask;

import com.superarh.tarefas_backend.model.ListTask;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskCreateDto;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListTaskMapper {
    ListTaskMapper INSTANCE = Mappers.getMapper(ListTaskMapper.class);
    ListTask toEntity(ListTaskCreateDto listTaskCreateDto);
    ListTask toUpdateEntity (ListTaskUpdateDto listTaskUpdateDto);
    ListTaskResponse toDto(ListTask listTask);

}
