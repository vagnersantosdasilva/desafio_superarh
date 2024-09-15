package com.superarh.tarefas_backend.model.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserResponse(

        Long id,

        String email,

        String name,


        Boolean actived
) {
}
