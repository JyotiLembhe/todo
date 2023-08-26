package com.todo.repository;

import com.todo.dto.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TodoRepository {

    private final List<Todo> todoStore = new CopyOnWriteArrayList<>();

    public int add(Todo todo) {
        todo.setId(todoStore.size());
        todoStore.add(todo);
        return todo.getId();
    }

    public Todo get(int i) {
        return todoStore.get(i);
    }

    public Todo remove(int i) {
        return todoStore.remove(i);
    }

    public Todo update(Todo todo) {
        todoStore.add(todo.getId(), todo);
        return todo;
    }

    public Optional<Todo> findById(int id) {
        return todoStore.stream().filter(todo -> todo.getId() == id).findFirst();
    }

    public List<Todo> findAll() {
        return todoStore;
    }
}
