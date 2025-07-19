package ru.job4j.todo.persistence;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task add(Task task);

    boolean update(Task task);

    boolean delete(int id);

    Optional<Task> findById(int id);

    List<Task> findAll(User user);

    List<Task> findTasks(boolean status);

    void complete(int id);
}
