package hei.school.dish_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hei.school.dish_application.dataSource.DataSource;

@SpringBootApplication
public class DishApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishApplication.class, args);
	}

	public DataSource dataSource(){
		String url = "";
		String user = "";
		String password = "";

		
	}
}
