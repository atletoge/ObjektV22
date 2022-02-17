package selfcheckout;

import java.util.List;
import java.util.ArrayList;

public class Discount {

    private String name;
    private double discountPercentage = 0.0;
    private List<Item> discountedItems;

    public Discount(String name, double discountPercentage, List<Item> discountedItems) {
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.discountedItems = new ArrayList<>(discountedItems);
    }

    public String getName() {
        return name;
    }

    public List<Item> getDiscountedItems() {
        return new ArrayList<>(discountedItems);
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}