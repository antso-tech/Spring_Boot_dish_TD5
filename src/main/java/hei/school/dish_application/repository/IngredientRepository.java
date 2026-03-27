package hei.school.dish_application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hei.school.dish_application.dataSource.DataSource;
import hei.school.dish_application.entity.CategoryEnum;
import hei.school.dish_application.entity.Ingredient;

@Component
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
            Ingredient ingredient = new Ingredient();
            int idIngredient = rs.getInt("id");
            String ingredientName = rs.getString("name");
            long ingredientPrice = rs.getLong("price");
            String categoryEnum = rs.getString("category");
            CategoryEnum category = CategoryEnum.valueOf(categoryEnum);

            ingredient.setId(idIngredient);
            ingredient.setName(ingredientName);
            ingredient.setPrice(ingredientPrice);
            ingredient.setCategory(category);

            ingredients.add(ingredient);

        }
        System.out.println(ingredients);

        return ingredients;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


}
