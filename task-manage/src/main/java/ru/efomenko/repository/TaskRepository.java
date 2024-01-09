package ru.efomenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.efomenko.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByIdIn(int[] ids);

    Optional<Task> findByIdAndTodoListOwnerId(int taskId, int userId);
}
