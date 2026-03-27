package hei.school.dish_application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hei.school.dish_application.repository.IngredientRepository;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientRepository ingredientRepository;
    
    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(ingredientRepository.getIngredients());
    }
    
}
