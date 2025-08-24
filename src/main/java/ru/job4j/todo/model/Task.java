package ru.job4j.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tasks")
public class Task {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    /**
     * Описание
     */
    private String description;
    /**
     * Дата создания
     */
    private LocalDateTime created;
    /**
     * Статус выполнения
     */
    private boolean done;

    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Приоритет задания
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    /**
     * Список категорий задания
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tasks_categories",
            joinColumns = { @JoinColumn(name = "task_id")},
            inverseJoinColumns = { @JoinColumn(name = "category_id")}
    )

    private List<Category> categoryList = new ArrayList<>();
}
