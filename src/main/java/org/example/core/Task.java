package org.example.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(name = "Task.getTasks", query = "SELECT t FROM Task t"),
        // Add more named queries as needed
        @NamedQuery(name="Task.getById", query = "SELECT t from Task t where t.id = :taskId"),
        @NamedQuery(
                name = "Task.updateTask",
                query = "UPDATE Task t SET " +
                        "t.title = CASE WHEN :title IS NOT NULL THEN :title ELSE t.title END, " +
                        "t.description = CASE WHEN :description IS NOT NULL THEN :description ELSE t.description END, " +
                        "t.status = CASE WHEN :status IS NOT NULL THEN :status ELSE t.status END, " +
                        "t.startDate = CASE WHEN :startDate IS NOT NULL THEN :startDate ELSE t.startDate END, " +
                        "t.targetDate = CASE WHEN :targetDate IS NOT NULL THEN :targetDate ELSE t.targetDate END " +
                        "WHERE t.id = :taskId"
        ),
        @NamedQuery(
                name = "Task.deleteTask",
                query = "DELETE FROM Task t WHERE t.id = :taskId"
        )
})
public class Task {
    // Entity class as before
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "startDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(name = "targetDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date targetDate;


    // Constructors

    public Task() {
        // Default constructor
    }

    public Task(long id, String title, String description, String status, Date startDate, Date targetDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    // Getter methods

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public String getStatus(){return status;}

    // Setter methods can be added if needed

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", targetDate=" + targetDate +
                '}';
    }
}
