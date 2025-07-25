package ru.job4j.todo.persistence;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final SessionFactory sf;

    private final CrudRepository crudRepository;

    @Override
    public Optional<User> add(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return crudRepository.optional("SELECT u FROM User AS u WHERE u.email = :fEmail AND "
                        + "u.password = :fPassword", User.class,
                Map.of("fEmail", email, "fPassword", password));
    }

    @Override
    public boolean update(User user) {
        String query = "UPDATE User u SET u.name = :fName, u.email = :fEmail, u.password = :fPassword WHERE u.id = :fId";
        Map<String, Object> params = Map.of(
                "fName", user.getName(),
                "fEmail", user.getEmail(),
                "fPassword", user.getPassword(),
                "fId", user.getId()
        );
        int updatedCount = crudRepository.executeUpdate(query, params);
        return updatedCount > 0;
    }
}
