package org.example.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.core.Task;
import org.hibernate.SessionFactory;
import java.util.List;
public class TodoDAO extends AbstractDAO<Task> {

    public TodoDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> getTasks() {
        // Use the Hibernate session for database operations
        return list(namedTypedQuery("Task.getTasks"));
    }

    public Task findByTaskId(int taskId){
        return uniqueResult(namedTypedQuery("Task.getById").setParameter("taskId", taskId));
    }

    public Task editTask(int taskId, Task newTask){
        namedQuery("Task.updateTask")
                .setParameter("title", newTask.getTitle())
                .setParameter("taskId", taskId)
                .setParameter("description", newTask.getDescription())
                .setParameter("startDate", newTask.getStartDate())
                .setParameter("status", newTask.getStatus())
                .setParameter("targetDate", newTask.getTargetDate()).executeUpdate();
        return findByTaskId(taskId);
    }

    public boolean deleteTask(int taskId){
        int res = namedQuery("Task.deleteTask").setParameter("taskId", taskId).executeUpdate();
        return res == 1;
    }

    public Task addTask(Task task) {
        return persist(task);
    }

    // Other methods as needed
}