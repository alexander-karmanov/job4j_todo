package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.persistence.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleTaskService implements TaskService {

    private final TaskRepository repository;

    public SimpleTaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task add(Task task) {
        return repository.add(task);
    }

    @Override
    public boolean update(Task task) {
        return repository.update(task);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public void complete(int id) {
        repository.complete(id);
    }

    @Override
    public Optional<Task> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findAll(User user) {
        return repository.findAll(user);
    }

    @Override
    public List<Task> findTasks(boolean status) {
        return repository.findTasks(status);
    }
}
