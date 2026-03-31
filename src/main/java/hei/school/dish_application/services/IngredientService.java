package hei.school.dish_application.services;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.entity.StockMovement;
import hei.school.dish_application.entity.StockValue;
import hei.school.dish_application.entity.UnitType;
import hei.school.dish_application.repository.DishRepository;
import hei.school.dish_application.repository.IngredientRepository;
import hei.school.dish_application.repository.StockRepository;
import hei.school.dish_application.validator.IngredientValidator;

@Service
public class IngredientService {
    
    private final IngredientRepository ingredientRepository;
    private final StockRepository stockRepository;
    private final IngredientValidator ingredientValidator;

    public IngredientService(IngredientRepository ingredientRepository, DishRepository dishRepository,
            StockRepository stockRepository, IngredientValidator ingredientValidator) {
        this.ingredientRepository = ingredientRepository;
        this.stockRepository = stockRepository;
        this.ingredientValidator = ingredientValidator;
    }

    public List<Ingredient> getAllIngredient(){
        return ingredientRepository.getIngredients();
    }

    public Ingredient getIngredientById(int id){
            
        ingredientValidator.getIngredientValidator(id);
        

        return ingredientRepository.getIngredientById(id);
      
        
    }
    
    public StockValue getStockValue(int ingredientId, String at, String unit){
        Instant InstantAt = ingredientValidator.validateAndConvertToDate(at);
        UnitType unitToType = ingredientValidator.validateAndConvertUnit(unit);

        return stockRepository.getStockValues(ingredientId, InstantAt, unitToType);
    }

    public List<StockMovement> getStockMovementsByTime(String from, String to, int idIngredients){
        ingredientValidator.getIngredientValidator(idIngredients);

        Instant instantFrom = ingredientValidator.validateAndConvertFrom(from);
        Instant instantTo = ingredientValidator.validateAndConvert(to);
        
        return ingredientRepository.findStockMovementDates(instantFrom, instantTo, idIngredients);
    }
    
}
