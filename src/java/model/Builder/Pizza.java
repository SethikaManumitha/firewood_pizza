package model.Builder;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private final String name;
    private final String crust;
    private final String sauce;
    private final List<String> toppings;
    private final boolean includeCheese;
    private final boolean isFavourite;
    private final String size; 
    private final float price; 
    private final int qty;     

    private Pizza(Builder builder) {    
        this.name = builder.name;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.includeCheese = builder.includeCheese;
        this.isFavourite = builder.isFavourite;
        this.size = builder.size; 
        this.price = builder.price; 
        this.qty = builder.qty;     
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public boolean isIncludeCheese() {
        return includeCheese;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public String getSize() {
        return size;  
    }

    public float getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    // Builder class
    public static class Builder {
        private String name;
        private String crust;
        private String sauce;
        private List<String> toppings = new ArrayList<>();
        private boolean includeCheese = false; 
        private boolean isFavourite = false;
        private String size = ""; 
        private float price; 
        private int qty;        

        // Setters
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public Builder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder addToppings(String[] toppingsArray) {
            if (toppingsArray != null) {
                for (String topping : toppingsArray) {
                    this.toppings.add(topping);
                }
            }
            return this;
        }

        public Builder includeCheese(boolean includeCheese) {
            this.includeCheese = includeCheese;
            return this;
        }

        public Builder setIsFavourite(boolean isFavourite) {
            this.isFavourite = isFavourite;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Builder setQty(int qty) {
            this.qty = qty;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}
