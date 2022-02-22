package selfcheckout;

import java.util.List;
import java.util.ArrayList;

public class Discount {

    private String name;
    private double discountPercentage = 0.0;
    private String category;
    private boolean memberDiscount;
    private List<String> activeWeekdays;

    public Discount(String name, double discountPercentage, String category, boolean memberDiscount,
            List<String> activeWeekdays) {
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.category = category;
        this.memberDiscount = memberDiscount;
        this.activeWeekdays = new ArrayList<>(activeWeekdays);
    }

    public String getName() {
        return name;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getCategory() {
        return category;
    }

    public boolean isMemberDiscount() {
        return memberDiscount;
    }

    public List<String> getActiveWeekdays() {
        return new ArrayList<>(activeWeekdays);
    }

    @Override
    public String toString() {
        return name + " -" + Math.round(discountPercentage * 100) + "%";
    }

}