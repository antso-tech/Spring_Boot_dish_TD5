package hei.school.dish_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hei.school.dish_application.dataSource.DataSource;

@SpringBootApplication
public class DishApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishApplication.class, args);
	}

	@Bean
	public DataSource dataSource(){
		String url = "jdbc:postgresql://localhost:5432/mini_dish_db";
		String user = "mini_dish_db_manager";
		String password = "dish_managment_password";

		return new DataSource(url, user, password);
	}
}
