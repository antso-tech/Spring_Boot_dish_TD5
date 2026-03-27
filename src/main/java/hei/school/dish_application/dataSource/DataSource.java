package hei.school.dish_application.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DataSource {
    private  DataSource instance;
    private String url;
    private String username;
    private String password;

    public DataSource() {
        this.url = "jdbc:postgresql://localhost:5432/mini_dish_db";
        this.username = "mini_dish_db_manager";
        this.password = "dish_managment_password";
    }

    public DataSource getInstance(){
        if(instance == null){
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);

    }

}
