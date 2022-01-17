package com.example.demo;

import com.example.demo.models.Contact;
import com.example.demo.repositories.ContactRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ContactRepository repository = new ContactRepository() ;
		Contact contact = new Contact("myid" ,"myname" , "mymail" , "myno" ,"myscore" , 0 , new Date())  ;
		repository.addContact(contact) ;
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Application started at http://localhost:8080");
	}

}

