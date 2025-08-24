package ru.job4j.todo.persistence;

import ru.job4j.todo.model.Category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmCategoryRepository implements CategoryRepository {
    private final CrudRepository crudRepository;

    @Override
    public List<Category> getAllCategory() {
        return crudRepository.query("FROM Category ORDER BY id", Category.class);
    }

    @Override
    public Optional<Category> findById(int id) {
        return crudRepository.optional("SELECT c FROM Category AS c WHERE c.id = :fId", Category.class, Map.of("fId", id));
    }

    @Override
    public List<Category> findByIds(List<Integer> ids) {
        return crudRepository.query("SELECT c FROM Category AS c WHERE c.id IN :ids", Category.class);
    }
}
