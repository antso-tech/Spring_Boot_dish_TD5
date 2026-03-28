package hei.school.dish_application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hei.school.dish_application.dataSource.DataSource;
import hei.school.dish_application.entity.Dish;
import hei.school.dish_application.entity.DishIngredient;
import hei.school.dish_application.entity.DishtypeEnum;

public class DishRepository {
    private DataSource dataSource;

    public DishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dish> getAllDishes(){
        try (Connection conn = dataSource.getConnection()){
            List<Dish> dishList = new ArrayList<>();
            String sql = """
            SELECT id, name, dishType, price FROM dish;
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Dish dish = new Dish();

                int idDish = rs.getInt("id");
                String dishName = rs.getString("name");
                String dishType = rs.getString("dishType");
                DishtypeEnum dishTypeToEnum = DishtypeEnum.valueOf(dishType);
                Long price = rs.getLong("price");

                dish.setId(idDish);
                dish.setName(dishName);
                dish.setDishType(dishTypeToEnum);
                dish.setPrice(price);
                dishList.add(dish);
            }

            return dishList; 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        


    }

    
}
