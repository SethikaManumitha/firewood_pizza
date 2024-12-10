package model;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private Crust crust;
    private Sauce sauce;
    private List<Topping> toppings;
    private int quantity;

   
    private Pizza(Builder builder) {
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.quantity = builder.quantity;
    }

    // Getters
    public Crust getCrust() {
        return crust;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public int getQuantity() {
        return quantity;
    }

    
    public double calculatePrice() {
        double totalPrice = crust.getPrice() + sauce.getPrice();
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice();
        }
        return totalPrice * quantity;
    }

    // Builder class
    public static class Builder {
        private Crust crust;
        private Sauce sauce;
        private List<Topping> toppings = new ArrayList<>();
        private int quantity = 1; 

        
        public Builder setCrust(Crust crust) {
            this.crust = crust;
            return this;
        }

        public Builder setSauce(Sauce sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder addTopping(Topping topping) {
            this.toppings.add(topping);
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}
