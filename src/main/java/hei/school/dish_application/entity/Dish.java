package hei.school.dish_application.entity;

import java.util.List;

public class Dish {
    private int id;
    private String name;
    private DishtypeEnum dishType;
    private long price;
    private List<DishIngredient> dishIngredients;
    
    public Dish() {
    }

    public Dish(int id, String name, DishtypeEnum dishType, long price, List<DishIngredient> dishIngredients) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.price = price;
        this.dishIngredients = dishIngredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishtypeEnum getDishType() {
        return dishType;
    }

    public void setDishType(DishtypeEnum dishType) {
        this.dishType = dishType;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<DishIngredient> getDishIngredients() {
        return dishIngredients;
    }

    public void setDishIngredients(List<DishIngredient> dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    @Override
    public String toString() {
        return "Dish [id=" + id + ", name=" + name + ", dishType=" + dishType + ", price=" + price + ", dishIngredients="
                + dishIngredients + "]";
    }    
}
