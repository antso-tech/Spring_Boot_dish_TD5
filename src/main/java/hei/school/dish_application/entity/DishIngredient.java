package hei.school.dish_application.entity;

public class DishIngredient {
    Ingredient ingredient;

    public DishIngredient( Ingredient ingredient) {

        this.ingredient = ingredient;
    }

    public DishIngredient() {
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
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
        if (ingredient == null) {
            if (other.ingredient != null)
                return false;
        } else if (!ingredient.equals(other.ingredient))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DishIngredient [ingredient=" + ingredient + "]";
    }

    
    
    
}
