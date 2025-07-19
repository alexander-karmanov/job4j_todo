package ru.job4j.todo.persistence;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> add(User user);

    Optional<User> findUserByEmailAndPassword(String email, String password);

    boolean update(User user);
}
