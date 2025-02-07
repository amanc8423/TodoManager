package com.lcwd.todo.todo.dao;

import com.lcwd.todo.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TodoDao {


    Logger logger = LoggerFactory.getLogger(TodoDao.class);



    private JdbcTemplate template;


    public TodoDao( @Autowired JdbcTemplate template) {
        this.template = template;
//        String createTable = "create TABLE IF NOT EXISTS todos(id int primary key,title varchar(100) not null,content varchar(500), status varchar(10) not null, addedDate datetime, todoDate datetime)";
//
//        int update =  template.update(createTable);
//
//        logger.info(" TODO TABLE CREATED {} ",update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;




    }

    //save todo in database

    public Todo saveTodo(Todo todo){
        String insertQuery = " insert into todos(id,title,content,status,addedDate,todoDate) values(?,?,?,?,?,?)";

    int rows =  template.update(insertQuery,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),
                todo.getAddedDate(),todo.getToDoDate());

        logger.info("JDBC OPERATION : { } inserted", rows );

        return todo;
    }


}
