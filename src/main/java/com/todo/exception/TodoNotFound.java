package com.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "todo not found",value = HttpStatus.NOT_FOUND)
public class TodoNotFound extends RuntimeException{
}
