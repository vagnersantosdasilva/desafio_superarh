package com.superarh.tarefas_backend.model.dto.ListTask;

import com.superarh.tarefas_backend.model.dto.Task.TaskResponse;
import com.superarh.tarefas_backend.model.dto.user.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public record ListTaskResponse(
        Long id,
        String name,
        LocalDateTime dateCreate,
        LocalDateTime whenToDo,
        List<TaskResponse> tasks,
        UserResponse user
) {
}
