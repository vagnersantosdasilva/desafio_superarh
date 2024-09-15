package com.superarh.tarefas_backend.model.dto.ListTask;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ListTaskCreateDto(
        @NotNull(message = "Name não pode ser vazio")
        String name,

        @NotNull(message="Deve definir data de realização das tarefas")
        LocalDateTime whenToDo

) {
}
