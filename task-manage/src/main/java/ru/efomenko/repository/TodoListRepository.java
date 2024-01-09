package ru.efomenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.efomenko.entity.TodoList;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    List<TodoList> findAllByOwnerId(int userId);

    TodoList findByOwnerIdAndId(int userId, int todoId);
}
