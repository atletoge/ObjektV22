package Eksamener.kont19.main.java.del1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bike {
    
    private Person renter;
    private GeoLocation location;
    private int gebyr;
    private List<RentalInfo> rentals = new ArrayList<>();
    private int price;

    
    public GeoLocation getLocation() {
        return location;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void addRentalInfo(RentalInfo rental) {
        if(!rentals.contains(rental)) {
            rentals.add(rental);
        }
    }

    public List<RentalInfo> getRentalInfos() {
        return new ArrayList<>(rentals);
    }

    public void clearRentals() {
        rentals.clear();
        this.price = 0;
        this.gebyr = 0;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public Person getRenter() {
        return renter;
    }

    public void setRenter(Person renter) {
        if(renter != null) throw new IllegalArgumentException("Can't set new renter before old one is removed");
        this.renter = renter;
    }

    public void removeRenter() {
        this.renter = null;
    }


    public void addGebyr(int n) {
        if(n == 5){
            gebyr += n;
        } else if(n == 10){
            gebyr += n;
        } else {
            throw new IllegalArgumentException("Invalid gebyr");
        }
    }

    public int getGebyr() {
        return gebyr;
    }
    
    // ??? 1 c)
    
}