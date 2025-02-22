package com.example.demo.finalPractice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.finalPractice.todo.Todo;
import java.util.function.Predicate;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "Akash","Learn AWS1", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todosCount, "Akash","Learn DevOps1", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++todosCount, "Akash","Learn Full Stack Development1	", 
				LocalDate.now().plusYears(3), false ));
	}
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
	
}
	
	public void deleteById(int id) {
		//todo.getId() == id
		// todo -> todo.getId() == id
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
	    todos.add(todo);
	}
}