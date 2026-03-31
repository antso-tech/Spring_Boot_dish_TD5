package hei.school.dish_application.repository;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hei.school.dish_application.dataSource.DataSource;
import hei.school.dish_application.entity.CategoryEnum;
import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.entity.StockMovement;
import hei.school.dish_application.entity.StockValue;
import hei.school.dish_application.entity.UnitType;

@Component
public class IngredientRepository{
    private DataSource dataSource;

    public IngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    } 

    public List<Ingredient> getIngredients(){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name, price, category from ingredient";
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

    public Ingredient getIngredientById(int id){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name, price, category FROM ingredient WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();

            Ingredient ingredientById = new Ingredient();

            if(rs.next()){
               
                int idIngredient = rs.getInt("id");
                String ingredientName = rs.getString("name");
                Long ingredientPrice = rs.getLong("price");
                String category = rs.getString("category");
                CategoryEnum categoryValue = CategoryEnum.valueOf(category);

                ingredientById.setId(idIngredient);
                ingredientById.setName(ingredientName);
                ingredientById.setPrice(ingredientPrice);
                ingredientById.setCategory(categoryValue);
                return ingredientById;
              
            }else{
                return null;
            }
          

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<StockMovement> findStockMovementDates(Instant startDate, Instant endDate, int idDish){
        try (Connection conn = dataSource.getConnection()){
            String sql = """
                    SELECT 
    id,
    creation_datetime,
    unit,
    value,
    type
FROM stock_movement 
WHERE id_ingredient = ? 
    AND creation_datetime BETWEEN ? AND ?
ORDER BY creation_datetime DESC;
                    """;

        List<StockMovement> stockMovements = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idDish);
        ps.setTimestamp(2, java.sql.Timestamp.from(startDate));
        ps.setTimestamp(3, java.sql.Timestamp.from(endDate));
        ResultSet rs = ps.executeQuery();

        
        StockMovement stockMovement = new StockMovement(0, null, null, null);

        if (rs.next()) {
            int idStock = rs.getInt("id");
            java.sql.Timestamp datetime = rs.getTimestamp("creation_datetime");
            Instant creationDateTime = datetime.toInstant();
            String unit = rs.getString("unit");
            UnitType unitType = UnitType.valueOf(unit);
            long value = rs.getLong("value");

            stockMovement.setId(idStock);
            stockMovement.setCreationDateTime(creationDateTime);
            
            StockValue stockValue = new StockValue();

            stockValue.setValue(value);
            stockValue.setUnit(unitType);

            stockMovement.setValue(stockValue);

            stockMovements.add(stockMovement);
            
        }
        return stockMovements;          
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
