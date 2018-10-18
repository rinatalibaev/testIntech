package ru.alibaev.myTest;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.alibaev.myTest.model.Documents;
import ru.alibaev.myTest.model.Roles;
import ru.alibaev.myTest.model.Users;

@MappedTypes({Users.class,Roles.class,Documents.class})
@MapperScan("ru.alibaev.myTest.mappers")
@SpringBootApplication
public class MyBatis3Application {

	public static void main(String[] args) {
		SpringApplication.run(MyBatis3Application.class, args);
	}
}
