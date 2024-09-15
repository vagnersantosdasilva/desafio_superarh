package com.superarh.tarefas_backend.model.mapper.User;


import com.superarh.tarefas_backend.model.User;

import com.superarh.tarefas_backend.model.dto.user.UserCreateDto;
import com.superarh.tarefas_backend.model.dto.user.UserResponse;
import com.superarh.tarefas_backend.model.dto.user.UserUpdateDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserCreateDto userCreateDto);
    User toUpdateEntity (UserUpdateDto userUpdateDto);
    UserResponse toDto(User user);

}
