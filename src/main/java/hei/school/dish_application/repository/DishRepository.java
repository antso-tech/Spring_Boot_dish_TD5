package hei.school.dish_application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hei.school.dish_application.dataSource.DataSource;
import hei.school.dish_application.entity.Dish;
import hei.school.dish_application.entity.DishIngredient;
import hei.school.dish_application.entity.DishtypeEnum;

@Component
public class DishRepository {
    
    private DataSource dataSource;

    public DishRepository(DataSource dataSource, IngredientRepository ingredientRepository) {
        this.dataSource = dataSource;
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


    public void associateDishIngredient(Connection conn, int idDish,List<DishIngredient> dishIngredients){
        try {
            String sql = """
        INSERT INTO 
        DishIngredient (id_dish, id_ingredient, quantity_required, unit) 
        VALUES (?,?,?,?::unit_type)
                    """;
        
            PreparedStatement ps = conn.prepareStatement(sql);
            for(DishIngredient dishElements : dishIngredients){
                ps.setInt(1, idDish);
                ps.setInt(2, dishElements.getIngredient().getId());
                ps.setLong(3, dishElements.getQuantity());
                ps.setObject(4, dishElements.getUnit().name());
                ps.addBatch();
            }
            
            int rowEffect = ps.executeUpdate();

            if (rowEffect > 1) {
               System.out.println("New ingredient deleted, number of row deleted " + rowEffect);
                
            }
         
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void detachDishIngredient(Connection conn,int idDish){
        

        try{
            String detachIngredient = """
                DELETE FROM DishIngredient WHERE id_dish = ?
            """;

            PreparedStatement ps = conn.prepareStatement(detachIngredient);
            ps.setInt(1, idDish);
            int rowEffect = ps.executeUpdate();
            System.out.println("New ingredient deleted, number of row deleted " + rowEffect);
                
          
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateIngredientList(List<DishIngredient> dishIngredients, int idDish){

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            detachDishIngredient(conn ,idDish);

            if(!dishIngredients.isEmpty()){
            associateDishIngredient(conn, idDish, dishIngredients);

           }

           conn.commit();
           conn.setAutoCommit(true);
                
            } catch (Exception e) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                throw new RuntimeException(e);
            }   
    }

    public Dish findDishById(int idDish){
        try (Connection conn = dataSource.getConnection()){
            String SQL = """
                    SELECT id, name, dishType, price from DISH WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, idDish);
            ResultSet rs = ps.executeQuery();

            Dish dish = new Dish();
              
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dishType = rs.getString("dishType");
                DishtypeEnum dishTypeEnum = DishtypeEnum.valueOf(dishType);
                long price = rs.getLong("price");

                dish.setId(id);
                dish.setName(name);
                dish.setDishType(dishTypeEnum);
                dish.setPrice(price);
                

                return dish;
            }else{
                return null;
            }

            
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }

    }

}
