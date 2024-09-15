package com.superarh.tarefas_backend.repository;

import com.superarh.tarefas_backend.model.ListTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListTaskRepository extends JpaRepository<ListTask, Long> {
    List<ListTask> findByUserId(Long userId);
}
