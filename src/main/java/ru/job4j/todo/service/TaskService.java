package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task add(Task task);

    boolean update(Task task);

    boolean delete(int id);

    Optional<Task> findById(int id);

    List<Task> findAll();

    List<Task> findTasks(boolean status);

    void complete(int id);
}
