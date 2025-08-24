package ru.job4j.todo.persistence;

import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAllCategory();

    Optional<Category> findById(int id);

    List<Category> findByIds(List<Integer> ids);
}
