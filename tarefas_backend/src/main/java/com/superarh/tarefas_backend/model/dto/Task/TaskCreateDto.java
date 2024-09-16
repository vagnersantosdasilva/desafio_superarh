package com.superarh.tarefas_backend.model.dto.Task;

import com.superarh.tarefas_backend.model.StatusTask;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TaskCreateDto(
        @NotNull(message = "Name não pode ser vazio")
        @Size(max = 255, message = "Tamanho máximo de campo é de 255 caracteres")
        String name,

        @NotNull(message = "Description não pode ser vazio")
        String description,

        @NotNull(message="Id ListTaks não pode ser vazio")
        Long listTaskId,


        StatusTask status,

        LocalDateTime dateStart,

        LocalDateTime dateFinish
    ){
}
