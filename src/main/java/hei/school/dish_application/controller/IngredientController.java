package hei.school.dish_application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hei.school.dish_application.entity.StockValue;
import hei.school.dish_application.entity.UnitType;
import hei.school.dish_application.repository.IngredientRepository;
import hei.school.dish_application.repository.StockRepository;

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
    private StockRepository stockRepository;
    
    public IngredientController(IngredientRepository ingredientRepository, StockRepository stockRepository) {
        this.ingredientRepository = ingredientRepository;
        this.stockRepository = stockRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getAllIngredients() {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(ingredientRepository.getIngredients());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientdById(@PathVariable("id") int ingredientId) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(ingredientRepository.getIngredientById(ingredientId));
    
    }
    
    @GetMapping("/{id}/stock")
    public ResponseEntity<StockValue> getStock(@PathVariable("id") int ingredientId, @RequestParam Instant at,@RequestParam UnitType unit) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(stockRepository.getStockValues(ingredientId, at, unit));
    }
    

    
}
