package org.example.resources;

import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.api.TaskResponse;
import org.example.core.Task;
import org.example.db.TodoDAO;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)

public class TodoResource {
    private List<String> tasks = new ArrayList<>();
    private final TodoDAO todoDAO;

    public TodoResource(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }


    @GET
    @Path("/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Task> getTasks() {
        List<TaskResponse> result = new ArrayList<>();
        List<Task> tasks = todoDAO.getTasks();
        return tasks;
    }

    @POST
    @Path("/add")
    @UnitOfWork
//    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Task addTask(Task task) throws ParseException {
        return todoDAO.addTask(task);
    }
    //    @Transactional(TransactionIsolationLevel.READ_UNCOMMITTED)
//    public Task addTask(@FormDataParam("title") String title, @FormDataParam("desc") String desc, @FormDataParam("startDate") String startDate, @FormDataParam("targetDate") String targetDate) throws ParseException {
////        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//
//        return todoDAO.addTask(1, title, desc, startDate,  targetDate);
////        return "task added";
//    }

    @GET
    @UnitOfWork
    @Path("/tasks/{id}")
    public Response getTaskById(@PathParam("id") int taskId){
        Task res = todoDAO.findByTaskId(taskId);
        if(res == null) return Response.ok("{}").build();
        return Response.ok(res).build();
    }

    @PUT
    @UnitOfWork
    @Path("/tasks/{id}")
    public Task editTask(@PathParam("id") int taskId, Task newTask){
        return todoDAO.editTask(taskId, newTask);
    }

    @DELETE
    @UnitOfWork
    @Path("/tasks/{id}")
    public boolean deleteTask(@PathParam("id") int taskId){
        return todoDAO.deleteTask(taskId);
    }
}
