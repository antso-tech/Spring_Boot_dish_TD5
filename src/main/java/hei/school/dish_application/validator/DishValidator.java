package hei.school.dish_application.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import hei.school.dish_application.entity.Dish;
import hei.school.dish_application.entity.DishIngredient;
import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.exception.BadRequestException;
import hei.school.dish_application.repository.DishRepository;

@Component
public class DishValidator {

    private final DishRepository dishRepository;

    public DishValidator(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void dishIdValidator(int idDish){
        
        Dish dish = dishRepository.findDishById(idDish);
    
        if (dish == null) {
            throw new BadRequestException("Dish.id=" + idDish + " is not found");
               
         }

    }

    public void dishIngredientValidator(int idDish, List<DishIngredient> dishIngredients){
        
        for(DishIngredient dishIngredient : dishIngredients){
          
            if (dishIngredient == null) {
                 throw new BadRequestException("Les ingredients doivent être obligatoire");
                
            }

        }
    }

}
