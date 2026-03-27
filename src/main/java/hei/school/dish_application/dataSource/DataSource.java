package hei.school.dish_application.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataSource {
    private String url;
    private String username;
    private String password;

    
 
    public DataSource(String url, String user, String password) {
        this.url = url;
        this.username = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);

    }

}
