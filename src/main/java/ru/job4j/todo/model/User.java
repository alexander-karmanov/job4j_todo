package ru.job4j.todo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;

@Data
@Entity
@Table(name = "todo_user")
public class User {
    /** Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private int id;

    /** Имя
    */
    private String name;

    /** Электронная почта
     */
    private String email;

    /** Пароль
     */
    private String password;

    /**
     * Часовой пояс пользователя
     */
    @Column(name = "user_zone")
    private String timeZone;
}
