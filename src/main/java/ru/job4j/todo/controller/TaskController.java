package ru.job4j.todo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.filter.UserSession;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.TaskService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getAll(Model model, HttpSession session) {
        User user = UserSession.getUser(session);
        List<Task> userTasks = taskService.findAll(user);
        model.addAttribute("user", user);
        model.addAttribute("tasks", userTasks);
        return "tasks/all";
    }

    @GetMapping("/newTasks")
    public String getNewTasksList(Model model, HttpSession session) {
        User user = UserSession.getUser(session);
        model.addAttribute("user", user);
        return "tasks/new";
    }

    @GetMapping("/doneTasks")
    public String getDoneTasksList(Model model, HttpSession session) {
        User user = UserSession.getUser(session);
        model.addAttribute("user", user);
        return "tasks/done";
    }

    @GetMapping("/getInfo/{id}")
    public String getInfo(Model model, @PathVariable("id") int id) {
        Optional<Task> optTask = taskService.findById(id);
        if (optTask.isEmpty()) {
            return "errors/error";
        }
        Task task = optTask.get();
        model.addAttribute("task", task);
        return "tasks/info";
    }

    @PostMapping("/complete/{id}")
    public String setCompleteStatus(@PathVariable("id") int id) {
        taskService.complete(id);
        return "redirect:/tasks/";
    }

    @GetMapping("/edit/{id}")
    public String getFormEditTask(@PathVariable("id") int id, Model model) {
        Optional<Task> task = taskService.findById(id);
        if (task.isEmpty()) {
            return "errors/error";
        }
        model.addAttribute("task", task.get());
        return "tasks/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Task task) {
        if (!taskService.update(task)) {
            return "errors/error";
        }
        return "redirect:/tasks/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") int id) {
        if (!taskService.delete(id)) {
            return "errors/error";
        }
        return "redirect:/tasks/";
    }
}
