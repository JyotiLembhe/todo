package com.todo.dto;

import jakarta.validation.constraints.NotEmpty;

public class Todo {
    private int id;

    @NotEmpty(message = "title cannot be null or empty")
    private String title;
    private Boolean isCompleted;

    public Todo() {
        isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
