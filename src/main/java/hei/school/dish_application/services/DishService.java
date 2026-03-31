package hei.school.dish_application.services;

import java.util.List;

import hei.school.dish_application.entity.Dish;
import hei.school.dish_application.repository.DishRepository;

public class DishService {
    public DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> findAllDishes(){
        return dishRepository.getAllDishes();
    }
    
    
    
   
}
