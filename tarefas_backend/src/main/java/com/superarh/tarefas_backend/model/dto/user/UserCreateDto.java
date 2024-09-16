package com.superarh.tarefas_backend.model.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
        @NotNull(message = "Email não pode ser vazio")
        @Size(max = 255, message = "Tamanho máximo de campo é de 255 caracteres")
        String email,
        @NotNull(message = "Nome não pode ser vazio")
        @Size(max = 255, message = "Tamanho máximo de campo é de 255 caracteres")
        String name,

        @NotNull(message = "Password não pode ser vazio")
        @Size(min = 6, max = 255, message = "Password deve ter entre 6 e 255 caracteres")
        String password,

        Boolean actived) {
}
