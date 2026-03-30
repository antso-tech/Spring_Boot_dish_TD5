package hei.school.dish_application.controller;

import hei.school.dish_application.repository.IngredientRepository;
import hei.school.dish_application.services.DishService;
import hei.school.dish_application.validator.DishValidator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hei.school.dish_application.entity.DishIngredient;
import hei.school.dish_application.exception.BadRequestException;
import hei.school.dish_application.repository.DishRepository;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private DishService dishService;
 
    
    public DishController(DishService dishService) {
        this.dishService = dishService;
        
    }

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        try{
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(dishService.findAllDishes());

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }    

    @PutMapping("/{id}/ingredients")
    public ResponseEntity<?> putDishIngredient(@PathVariable int id,@RequestBody(required = false) List<DishIngredient> ingredients){
        try {

            dishValidator.dishIdValidator(id, ingredients);
      
            dishService.

            return ResponseEntity.ok().build();
            
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
            
        }
    }
}
