package hei.school.dish_application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hei.school.dish_application.exception.BadRequestException;
import hei.school.dish_application.repository.StockRepository;
import hei.school.dish_application.services.IngredientService;
import hei.school.dish_application.validator.IngredientValidator;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;
    private IngredientValidator ingredientValidator;

    public IngredientController(IngredientService ingredientService, StockRepository stockRepository, IngredientValidator ingredientValidator) {
        this.ingredientService = ingredientService;
        this.ingredientValidator = ingredientValidator;
    }

    @GetMapping()
    public ResponseEntity<?> getAllIngredients() {
        

        return ResponseEntity
        .status(HttpStatusCode.valueOf(200))
        .body(ingredientService.getAllIngredient());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientdById(@PathVariable("id") int ingredientId) {
        try {
 
            ingredientValidator.getIngredientValidator(ingredientId);

        return ResponseEntity
        .status(HttpStatusCode.valueOf(200))
        .body(ingredientService.getIngredientById(ingredientId));            
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
            
        }

    
    }
    
    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStock(@PathVariable("id") int ingredientId, @RequestParam String at,@RequestParam String unit) {

  

        try {
            ingredientValidator.getIngredientValidator(ingredientId);

            return ResponseEntity.status(HttpStatusCode.valueOf(200))
            .body(ingredientService.getStockValue(ingredientId, at, unit));

        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(e.getMessage());
            
        }       

    }

    @GetMapping("/{id}/stockMovements")
    public ResponseEntity<?> getStockHourEntity(@PathVariable("id") int idIngredient ,@RequestParam String from, @RequestParam String to){
        return ResponseEntity.status(200).body(ingredientService.gMovements(from, to, idIngredient));
        
    }

    
    
}
