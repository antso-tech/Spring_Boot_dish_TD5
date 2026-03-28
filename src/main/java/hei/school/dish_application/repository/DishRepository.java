package hei.school.dish_application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hei.school.dish_application.dataSource.DataSource;
import hei.school.dish_application.entity.Dish;
import hei.school.dish_application.entity.DishIngredient;
import hei.school.dish_application.entity.DishtypeEnum;
import hei.school.dish_application.entity.UnitType;

@Component
public class DishRepository {
    private final IngredientRepository ingredientRepository;
    private DataSource dataSource;

    public DishRepository(DataSource dataSource, IngredientRepository ingredientRepository) {
        this.dataSource = dataSource;
        this.ingredientRepository = ingredientRepository;
    }

    public List<DishIngredient> findDishIngredientByIdDish(int dishId){
        IngredientRepository ingredientRepository = new IngredientRepository(dataSource);

        try(Connection conn = dataSource.getConnection()){
            String sql = """
                    SELECT id_ingredient
                    FROM dishIngredient 
                    WHERE id_dish = ?
                    """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dishId);
            ResultSet rs = ps.executeQuery();
            List<DishIngredient> dishIngredients = new ArrayList<>();
            
            while (rs.next()) {
                DishIngredient dishIngredient = new DishIngredient();
               
                int idIngredient = rs.getInt("id_ingredient");

                dishIngredient.setIngredient(ingredientRepository.getIngredientById(idIngredient));   
                dishIngredients.add(dishIngredient);
                
            }
        System.out.println(dishIngredients);
        return dishIngredients;

        }catch(Exception e){
            throw new RuntimeException(e);
        }

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
                dish.setDishIngredients(findDishIngredientByIdDish(idDish));
                dishList.add(dish);
            }

            return dishList; 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        


    }

    
}
