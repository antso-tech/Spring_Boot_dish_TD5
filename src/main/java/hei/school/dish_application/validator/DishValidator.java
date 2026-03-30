package hei.school.dish_application.validator;

import java.util.List;

import hei.school.dish_application.entity.DishIngredient;
import hei.school.dish_application.entity.Ingredient;
import hei.school.dish_application.exception.BadRequestException;
import hei.school.dish_application.repository.DishRepository;

public class DishValidator {

    private final DishRepository dishRepository;

    public DishValidator(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void DishValidator(int idDish){
        
        List<DishIngredient> dishIngredient = dishRepository.findDishIngredientByIdDish(idDish);

        for(DishIngredient dIngredient : dishIngredient){
            if (dIngredient == null) {
                throw new BadRequestException("Dish.id=" + idDish + " is not found");
               
            }

        }
    }

}
