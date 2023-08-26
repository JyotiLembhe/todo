package service;

import com.todo.dto.Todo;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    Todo getTodo(){
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("hello");
        todo.setIsCompleted(false);
        return todo;
    }

    @Test
   void testSaveTodo(){
        var todo = getTodo();
        Mockito.when(todoRepository.add(todo)).thenReturn(1);
        int result = todoService.saveTodo(todo);
        Assertions.assertEquals(1,result);
    }

    @Test
    void testGetTodo() throws Exception {
        var todo = getTodo();
        Mockito.when(todoRepository.get(1)).thenReturn(todo);
        Todo result = todoService.getTodo(1);
        Assertions.assertEquals(todo,result);
    }

    @Test
    void testDelete() throws Exception {
        Mockito.doNothing().when(todoRepository).remove(1);
        todoService.delete(1);
        Mockito.verify(todoRepository,Mockito.times(1)).remove(1);
    }

    @Test
    void testFindById() {
        var todo  = getTodo();
        Mockito.when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        Todo result = todoService.findById(1);
        Assertions.assertEquals(todo,result);


    }

    @Test
    void testFindAll() {
        List<Todo> todoList = List.of(getTodo());
        Mockito.when(todoRepository.findAll()).thenReturn(todoList);
        List<Todo> result = todoService.findAll();
        Assertions.assertEquals(todoList,result);

    }

    @Test
    public void testUpdateTodo() {
        Todo currentTodo = getTodo();
        Todo updateTodo = new Todo();
        updateTodo.setId(1);
        updateTodo.setTitle("Hello123");
        updateTodo.setIsCompleted(false);
        Mockito.when(todoRepository.findById(1)).thenReturn(Optional.of(currentTodo));
        Mockito.when(todoRepository.update(currentTodo)).thenReturn(updateTodo);
        Todo updatedTodo = todoService.updateTodo(1,updateTodo);
        Assertions.assertEquals(updateTodo,updatedTodo);

    }

}
