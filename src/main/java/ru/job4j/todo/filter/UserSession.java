package ru.job4j.todo.filter;

import jakarta.servlet.http.HttpSession;
import ru.job4j.todo.model.User;

public class UserSession {

    private UserSession() {
    }

    public static User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
