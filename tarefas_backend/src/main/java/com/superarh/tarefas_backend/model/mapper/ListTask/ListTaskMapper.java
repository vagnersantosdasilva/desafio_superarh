package com.superarh.tarefas_backend.model.mapper.ListTask;

import com.superarh.tarefas_backend.model.ListTask;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskCreateDto;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskResponse;
import com.superarh.tarefas_backend.model.dto.ListTask.ListTaskUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListTaskMapper {
    ListTaskMapper INSTANCE = Mappers.getMapper(ListTaskMapper.class);
    ListTask toEntity(ListTaskCreateDto listTaskCreateDto);
    ListTask toUpdateEntity (ListTaskUpdateDto listTaskUpdateDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "tasks", target = "tasks") // Mapeamento do ID diretamente
    @Mapping(source = "user", target = "user")
    ListTaskResponse toDto(ListTask listTask);
}
