package com.superarh.tarefas_backend.model.dto.ListTask;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ListTaskCreateDto(
        @NotNull(message = "Name não pode ser vazio")
        @Size(max = 255, message = "Tamanho máximo de campo é de 255 caracteres")
        String name,

        @NotNull(message="Deve definir data de realização das tarefas")
        LocalDateTime whenToDo

) {
}
