package com.lcwd.todo.todo;

import com.lcwd.todo.todo.dao.TodoDao;
import com.lcwd.todo.todo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
	@Autowired
private TodoDao todoDao;

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Template: " + todoDao.getTemplate());


		Todo todo = new Todo();
		todo.setId(123);
		todo.setTitle("This is testing spring jdbc");
		todo.setContent("wow its working");
		todo.setStatus("PENDING");
		Date date = new Date();
		todo.setAddedDate(date);
		todo.setToDoDate(date);

		todoDao.saveTodo(todo);
	}

}
