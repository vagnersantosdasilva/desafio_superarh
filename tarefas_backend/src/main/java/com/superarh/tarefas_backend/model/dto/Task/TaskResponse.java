package com.superarh.tarefas_backend.model.dto.Task;

import com.superarh.tarefas_backend.model.ListTask;
import com.superarh.tarefas_backend.model.StatusTask;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String name,
        String description,
        Long listTaskId,
        StatusTask status ,
        LocalDateTime dateStart,
        LocalDateTime dateFinish
) {
}
