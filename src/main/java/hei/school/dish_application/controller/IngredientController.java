package hei.school.dish_application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import hei.school.dish_application.entity.UnitType;
import hei.school.dish_application.exception.BadRequestException;
import hei.school.dish_application.repository.IngredientRepository;
import hei.school.dish_application.repository.StockRepository;
import hei.school.dish_application.validator.IngredientValidator;

import java.time.Instant;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientRepository ingredientRepository;
    private IngredientValidator ingredientValidator;
    private StockRepository stockRepository;
    
    public IngredientController(IngredientRepository ingredientRepository, StockRepository stockRepository, IngredientValidator ingredientValidator) {
        this.ingredientRepository = ingredientRepository;
        this.stockRepository = stockRepository;
        this.ingredientValidator = ingredientValidator;
    }

    @GetMapping()
    public ResponseEntity<?> getAllIngredients() {
        

        return ResponseEntity
        .status(HttpStatusCode.valueOf(200))
        .body(ingredientRepository.getIngredients());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientdById(@PathVariable("id") int ingredientId) {
        try {
 
            ingredientValidator.getIngredientValidator(ingredientId);

        return ResponseEntity
        .status(HttpStatusCode.valueOf(200))
        .body(ingredientRepository.getIngredientById(ingredientId));            
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
            
        }

    
    }
    
    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStock(@PathVariable("id") int ingredientId, @RequestParam String at,@RequestParam String unit) {

  

        try {
            ingredientValidator.getIngredientValidator(ingredientId);
            
            Instant instantAt = ingredientValidator.validateAndConvertToDate(at);
            UnitType unitToType = ingredientValidator.validateAndConvertUnit(unit);


            return ResponseEntity
            .status(HttpStatusCode.valueOf(200))
            .body(stockRepository.getStockValues(ingredientId,instantAt , unitToType));

        } catch (BadRequestException e) {
            return ResponseEntity.status(400).body(e.getMessage());
            
        }       

    }
    
}
