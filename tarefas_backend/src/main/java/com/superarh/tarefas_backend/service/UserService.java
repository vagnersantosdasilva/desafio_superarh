package com.superarh.tarefas_backend.service;

import com.superarh.tarefas_backend.exception.ResourceNotFound;
import com.superarh.tarefas_backend.model.User;
import com.superarh.tarefas_backend.model.dto.user.UserCreateDto;
import com.superarh.tarefas_backend.model.dto.user.UserResponse;
import com.superarh.tarefas_backend.model.dto.user.UserUpdateDto;
import com.superarh.tarefas_backend.model.mapper.User.UserMapper;
import com.superarh.tarefas_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse create(UserCreateDto userCreateDto){
        User newUser = userRepository.save(UserMapper.INSTANCE.toEntity(userCreateDto));
        return UserMapper.INSTANCE.toDto(newUser);
    }

    public UserResponse update(UserUpdateDto userUpdateDto, Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            if (userUpdateDto.id().equals(id)){
                User userUpdated = UserMapper.INSTANCE.toUpdateEntity(userUpdateDto);
                return UserMapper.INSTANCE.toDto(userRepository.save(userUpdated));
            }
            throw new IllegalArgumentException("URI fora do padrão : ID em uri diferente de id em corpo da requisição");
        }
        throw new ResourceNotFound("Usuário não encontrado!");
    }

    public UserResponse findById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            return UserMapper.INSTANCE.toDto(userOptional.get());
        }
        throw new ResourceNotFound("Usuario não encontrado!");
    }


}
