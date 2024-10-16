package edu.api.bookflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class BookflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookflowApplication.class, args);
	}

}
