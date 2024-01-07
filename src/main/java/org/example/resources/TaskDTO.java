package org.example.resources;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api")

public class TaskDTO {
    int id;
     String task;
     String createdAt;
     String dueDate;

    // Constructors, getters, setters, etc.

    // For simplicity, you can use Lombok or manually create getters and setters
}
