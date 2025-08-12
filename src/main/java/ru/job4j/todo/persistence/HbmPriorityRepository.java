package ru.job4j.todo.persistence;

import lombok.AllArgsConstructor;
import ru.job4j.todo.model.Priority;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPriorityRepository implements PriorityRepository {
    private final CrudRepository crudRepository;

    @Override
    public List<Priority> getAllPriority() {
        return crudRepository.query(
                "FROM Priority", Priority.class
        );
    }

    @Override
    public Optional<Priority> findById(int id) {
        return crudRepository.optional(
                "FROM Priority WHERE id = :fId", Priority.class,
                Map.of("fId", id)
        );
    }
}
