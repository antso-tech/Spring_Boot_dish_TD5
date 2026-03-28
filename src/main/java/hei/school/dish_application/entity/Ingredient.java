package hei.school.dish_application.entity;

import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private long price;
    private CategoryEnum category;
    private List<StockMovement> stockMovementList;
    
    
    public Ingredient() {
    }

    public Ingredient(int id, String name, long price, CategoryEnum category, List<StockMovement> stockMovementList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockMovementList = stockMovementList;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public List<StockMovement> getStockMovementList() {
        return stockMovementList;
    }

    public void setStockMovementList(List<StockMovement> stockMovementList) {
        this.stockMovementList = stockMovementList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (price ^ (price >>> 32));
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((stockMovementList == null) ? 0 : stockMovementList.hashCode());
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
        Ingredient other = (Ingredient) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price != other.price)
            return false;
        if (category != other.category)
            return false;
        if (stockMovementList == null) {
            if (other.stockMovementList != null)
                return false;
        } else if (!stockMovementList.equals(other.stockMovementList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category
                + ", stockMovementList=" + stockMovementList + "]";
    }
     
}
