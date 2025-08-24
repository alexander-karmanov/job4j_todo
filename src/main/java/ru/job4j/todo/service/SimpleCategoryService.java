package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.persistence.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    public SimpleCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findByIds(List<Integer> ids) {
        return categoryRepository.findByIds(ids);
    }

}
