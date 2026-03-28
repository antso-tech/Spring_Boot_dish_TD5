package hei.school.dish_application.entity;

public class DishIngredient {
    int id;
    Dish dish;
    Ingredient ingredient;
    double quantity;
    UnitType unit;

    public DishIngredient(int id, Dish dish, Ingredient ingredient, double quantity, UnitType unit) {
        this.id = id;
        this.dish = dish;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public DishIngredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public UnitType getUnit() {
        return unit;
    }

    public void setUnit(UnitType unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((dish == null) ? 0 : dish.hashCode());
        result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
        long temp;
        temp = Double.doubleToLongBits(quantity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DishIngredient other = (DishIngredient) obj;
        if (id != other.id)
            return false;
        if (dish == null) {
            if (other.dish != null)
                return false;
        } else if (!dish.equals(other.dish))
            return false;
        if (ingredient == null) {
            if (other.ingredient != null)
                return false;
        } else if (!ingredient.equals(other.ingredient))
            return false;
        if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
            return false;
        if (unit != other.unit)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DishIngredient [id=" + id + ", dish=" + dish + ", ingredient=" + ingredient + ", quantity=" + quantity
                + ", unit=" + unit + "]";
    }
    
    
    
}
