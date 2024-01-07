package org.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskResponse {
    private final int id;
    private final String task;
    private final String dueDate;
    private String createdAt;

    public TaskResponse(int id, String task, String dueDate, String createdAt){
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;
    }

    @JsonProperty
    public int getId(){
        return id;
    }

    @JsonProperty
    public String getTask(){
        return task;
    }

    @JsonProperty
    public String getDueDate(){
        return dueDate;
    }

}
