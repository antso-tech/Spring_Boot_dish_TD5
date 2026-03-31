package hei.school.dish_application.validator;

import java.time.DateTimeException;
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

    public Instant validateAndConvertToDate(String at){
        if(at == null || at.isBlank()){
            throw new BadRequestException("""
                Either mandatory query parameter `at` or `unit` is not provided
                """);

        }
        try {
            return Instant.parse(at);
        } catch (DateTimeException e) {
            throw new BadRequestException("""
            Invalid date format for 'at'. Use ISO-8601 format (e.g., 2024-01-06T12:00:00Z)
            """);
        }

    }

    public Instant validateAndConvertFrom(String from){
        if(from == null || from.isBlank()){
            throw new BadRequestException("""
                Either mandatory query parameter `from` or `to` is not provided
                """);
        }
       return Instant.parse(from);
    }

        public Instant validateAndConvert( String to){
        if(to == null || to.isBlank()){
            throw new BadRequestException("""
                Either mandatory query parameter `from` or `to` is not provided
                """);

        }
        return Instant.parse(to);
    
    }

       public UnitType validateAndConvertUnit(String unit) {
        if (unit == null || unit.isBlank()) {
            throw new BadRequestException("Either mandatory query parameter `at` or `unit` is not provided.");
        }
        
        try {
            return UnitType.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unit must be PCS, KG or L" + unit);
        }
    }

}
