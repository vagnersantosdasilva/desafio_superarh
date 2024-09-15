package com.superarh.tarefas_backend.model.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @NotNull(message = "id não pode ser vazio")
        Long id,
        @NotNull(message = "Email não pode ser vazio")
        String email,
        @NotNull(message = "Nome não pode ser vazio")
        String name,

        @NotNull(message = "Senha não pode ser vazio")
        @Size(min = 6, max = 255)
        String password,

        Boolean actived
) {
}
