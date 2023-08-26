package controller;

import com.todo.controller.TodoController;
import com.todo.dto.Todo;
import com.todo.service.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;


    @Test
    public void testSaveTodo() throws Exception {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("hello");
        todo.setIsCompleted(false);

        Mockito.when(todoService.saveTodo(todo)).thenReturn(1);
        ResponseEntity<Todo> responseEntity = todoController.saveTodo(todo);
        Assertions.assertThat(responseEntity.getHeaders().getLocation()).isEqualTo(URI.create("http://localhost:8080/todo/1"));
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testfindAll() {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("hello");
        todo.setIsCompleted(false);
        List<Todo> list = new ArrayList<>();
        list.add(todo);
        Mockito.when(todoService.findAll()).thenReturn(list);
        ResponseEntity<List<Todo>> responseEntity = todoController.listAll();
        Assertions.assertThat(responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetToDo() {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("hello");
        todo.setIsCompleted(false);
        Integer i=1;
        Mockito.when(todoService.getTodo(i)).thenReturn(todo);
        ResponseEntity<Todo> responseEntity = todoController.getTodo(i);
        Assertions.assertThat(responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testRemoveTodo() {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("hello");
        todo.setIsCompleted(false);
        Integer i=1;
        doNothing().when(todoService).delete(i);
        ResponseEntity<Todo> responseEntity = todoController.removeTodo(i);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(todoService,times(1)).delete(i);
    }

    @Test
    public void testUpdateTodo() {
        Todo newTodo = new Todo();
        newTodo.setId(1);
        newTodo.setTitle("hello");
        newTodo.setIsCompleted(false);
        Integer i=1;
        Mockito.when(todoService.updateTodo(i,newTodo)).thenReturn(newTodo);
        ResponseEntity<Todo> responseEntity = todoController.updateTodo(i,newTodo);
        Assertions.assertThat(responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
