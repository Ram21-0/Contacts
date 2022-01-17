package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Application started at http://localhost:8080");
	}

}

/**
 *
 insert into contacts(name,email) values("Ram","ram@gmail.com")

 insert into contacts(name,email) values("Sita","ram@gmail.com");
 insert into contacts(name,email) values("Tom","tom@gmail.com");
 insert into contacts(name,email) values("Sam","sam@gmail.com");
 insert into contacts(name,email) values("Aamir","aamir@gmail.com");
 insert into contacts(name,email) values("Ananya","ananya@gmail.com");

 create table contacts (
 	id int auto_increment primary key,
 	name varchar(20),
 	email varchar(20),
 	score int default 0
 );
 */