package hei.school.dish_application.validator;

import java.time.Instant;

import org.springframework.stereotype.Component;

import hei.school.dish_application.exception.*;
import hei.school.dish_application.repository.IngredientRepository;
import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.entity.UnitType;

@Component
public class IngredientValidator {

    private final IngredientRepository ingredientRepository;

    public IngredientValidator(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void getIngredientValidator(Integer idIngredient) {

        Ingredient ingredient = ingredientRepository.getIngredientById(idIngredient);

        if (ingredient == null) {
                
            throw new BadRequestException("Ingredient.id="+ idIngredient + " is not found");
            
        }

    }

    public void getStockValidator(Instant at, UnitType unit){
        if(at == null){
            throw new BadRequestException("Either mandatory query parameter `at` or `unit` is not provided");

        }else if(unit == null){
            throw new BadRequestException("Either mandatory query parameter `at` or `unit` is not provided");
        }
        
    }
}
