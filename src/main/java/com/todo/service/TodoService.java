package com.todo.service;

import com.todo.dto.Todo;
import com.todo.exception.TodoNotFound;
import com.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public int saveTodo(Todo todo) {
        return todoRepository.add(todo);
    }

    public Todo getTodo(int i) {
        try {
            return todoRepository.get(i);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new TodoNotFound();
        }
    }

    public void delete(int i) {
        try {
            todoRepository.remove(i);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new TodoNotFound();
        }
    }

    public Todo updateTodo(Integer todoId, Todo newtTodo) {
        Optional<Todo> todoOptional = findById(todoId);
        if ( !todoOptional.isPresent() ) {
            throw new TodoNotFound();
        }
        delete(todoOptional.get().getId());
        newtTodo.setId(todoId);
        try {
            return todoRepository.update(newtTodo);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new TodoNotFound();
        }
    }

    public Optional<Todo> findById(int id) {
        return todoRepository.findById(id);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
