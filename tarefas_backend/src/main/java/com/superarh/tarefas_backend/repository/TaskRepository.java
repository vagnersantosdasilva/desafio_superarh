package com.superarh.tarefas_backend.repository;

import com.superarh.tarefas_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByListTaskId(Long idListTask);
}
