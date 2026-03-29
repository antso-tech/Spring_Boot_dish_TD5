package hei.school.dish_application.services;

import java.time.Instant;
import java.util.List;

import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.entity.StockValue;
import hei.school.dish_application.entity.UnitType;
import hei.school.dish_application.repository.DishRepository;
import hei.school.dish_application.repository.IngredientRepository;
import hei.school.dish_application.repository.StockRepository;

public class IngredientService {
    
    private final IngredientRepository ingredientRepository;
    private final StockRepository stockRepository;

    public IngredientService(IngredientRepository ingredientRepository, DishRepository dishRepository,
            StockRepository stockRepository) {
        this.ingredientRepository = ingredientRepository;
        this.stockRepository = stockRepository;
    }

    public List<Ingredient> getAllIngredient(){
        return ingredientRepository.getIngredients();
    }

    public Ingredient getIngredientById(int id){
        return ingredientRepository.getIngredientById(id);
    }
    
    public StockValue getStockValue(int ingredientId, Instant at, UnitType unit){
        return stockRepository.getStockValues(ingredientId, at, unit);
    }
    
}
