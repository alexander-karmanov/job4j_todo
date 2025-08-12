package ru.job4j.todo.persistence;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {

    private final SessionFactory sf;

    private final CrudRepository crudRepository;

    @Override
    public Task add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            String hql = "UPDATE Task SET description = :description, done = :done, priority.id = :priorityId WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("description", task.getDescription());
            query.setParameter("done", task.isDone());
            query.setParameter("priorityId", task.getPriority().getId());
            query.setParameter("id", task.getId());
            int result = query.executeUpdate();
            session.getTransaction().commit();
            return result > 0;
        } catch (Exception e) {
                session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.delete(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Optional<Task> findById(int id) {
        Task task = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            String hql = "FROM Task t LEFT JOIN FETCH t.priority WHERE t.id = :id";
            Query query = session.createQuery(hql, Task.class);
            query.setParameter("id", id);
            List<Task> results = query.getResultList();
            if (!results.isEmpty()) {
                task = results.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.ofNullable(task);
    }

    @Override
    public List<Task> findAll(User user) {
        Session session = sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            session.beginTransaction();
            String hql = "FROM ru.job4j.todo.model.Task t JOIN FETCH t.priority WHERE t.user.id = :userId";
            result = session.createQuery(hql, Task.class)
                    .setParameter("userId", user.getId())
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findTasks(boolean status, User user) {
        Session session = sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery(
                            "FROM ru.job4j.todo.model.Task t JOIN FETCH t.priority WHERE t.done = :fDone AND t.user.id = :fUser_id", Task.class)
                    .setParameter("fDone", status)
                    .setParameter("fUser_id", user.getId())
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void complete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            if (task != null) {
                task.setDone(true);
                session.update(task);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
