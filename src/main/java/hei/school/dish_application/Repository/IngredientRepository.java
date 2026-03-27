package hei.school.dish_application.Repository;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hei.school.dish_application.DataSource.DataSource;
import hei.school.dish_application.Entity.Ingredient;

public class IngredientRepository{
    private DataSource dataSource;

    public IngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    } 

    public List<Ingredient> getIngredients(){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name, price, category from ingredient;";
            PreparedStatement statement = conn.prepareStatement(sql);

        List<Ingredient> ingredients = new ArrayList<>();
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            int idIngredient = rs.getInt("id");
            String IngredientName = rs.getString("name");
            long IngredientPrice = rs.getLong("price");
            


        }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


}
