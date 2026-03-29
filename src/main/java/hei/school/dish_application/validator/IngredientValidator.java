package hei.school.dish_application.validator;

import java.time.Instant;
import java.time.LocalDate;

import org.apache.coyote.BadRequestException;

import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.entity.UnitType;

public class IngredientValidator {
    public void getIngredientValidator(Integer idIngredient) throws BadRequestException{
        if(idIngredient == null){
            throw new BadRequestException("Ingredient.id="+ idIngredient + " is not found");
        }

    }

    public void getStockValidator(Instant at, UnitType unit) throws BadRequestException{
        if(at == null){
            throw new BadRequestException("Either mandatory query parameter `at` or `unit` is not provided");

        }else if(unit == null){
            throw new BadRequestException("Either mandatory query parameter `at` or `unit` is not provided");
        }
        
    }
}
