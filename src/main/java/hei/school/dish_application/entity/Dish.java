package hei.school.dish_application.entity;



public class Dish {
    private int id;
    private String name;
    private DishtypeEnum dishType;
    private Long price;
    
    public Dish() {
    }

    public Dish(int id, String name, DishtypeEnum dishType, Long price) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.price = price;
      
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((dishType == null) ? 0 : dishType.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        Dish other = (Dish) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (dishType != other.dishType)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    

    
    
}
