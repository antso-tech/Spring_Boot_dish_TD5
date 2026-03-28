package hei.school.dish_application.controller;

import hei.school.dish_application.repository.IngredientRepository;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hei.school.dish_application.repository.DishRepository;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/dishes")
public class DishController {
    private final IngredientRepository ingredientRepository;
    private DishRepository dishRepository;
    
    public DishController(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        try{
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(dishRepository.getAllDishes());

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }    
}
