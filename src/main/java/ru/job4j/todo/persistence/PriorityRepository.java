package ru.job4j.todo.persistence;

import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository {

    List<Priority> getAllPriority();

    Optional<Priority> findById(int id);
}
