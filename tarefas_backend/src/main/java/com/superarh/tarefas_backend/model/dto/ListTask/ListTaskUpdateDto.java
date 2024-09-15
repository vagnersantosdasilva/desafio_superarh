package com.superarh.tarefas_backend.model.dto.ListTask;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ListTaskUpdateDto(
        @NotNull(message="Id não pode ser vazio")
        Long id,

        @NotNull(message="Name não pode ser vazio")
        String name,

        @NotNull(message = "whenToDo não pode ser vazio")
        LocalDateTime whenToDo
) {
}
