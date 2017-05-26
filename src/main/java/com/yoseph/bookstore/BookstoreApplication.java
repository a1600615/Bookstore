package com.yoseph.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yoseph.bookstore.domain.Book;
import com.yoseph.bookstore.domain.BookRepository;
import com.yoseph.bookstore.domain.Category;
import com.yoseph.bookstore.domain.CategoryRepository;
import com.yoseph.bookstore.domain.User;
import com.yoseph.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstore(BookRepository brepository,CategoryRepository crepository,UserRepository urepository){
		return (args) -> {
			log.info("Insert few books and categories");
			
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Sci-fi"));
			crepository.save(new Category("Biography"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Drama"));
			
			brepository.save(new Book("new book","someone",1956,"15489785-23",200,crepository.findByName("Horror").get(0)));
			brepository.save(new Book("new book","someone",1956,"15489785-23",200,crepository.findByName("Drama").get(0)));
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}
}
