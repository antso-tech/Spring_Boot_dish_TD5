package hei.school.dish_application.Entity;

public class StockValue {
    private double value;
    private UnitType unit;
    
    public StockValue(double value, UnitType unit) {
        this.value = value;
        this.unit = unit;
    }

    

    public double getValue() {
        return value;
    }



    public void setValue(double value) {
        this.value = value;
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
        long temp;
        temp = Double.doubleToLongBits(value);
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
        StockValue other = (StockValue) obj;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        if (unit != other.unit)
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "StockValue [value=" + value + ", unit=" + unit + "]";
    }

    
}
