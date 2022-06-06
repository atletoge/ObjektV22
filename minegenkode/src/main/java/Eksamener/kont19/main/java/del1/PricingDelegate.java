package Eksamener.kont19.main.java.del1;

import java.time.LocalDateTime;

public class PricingDelegate implements PricingDelegateDefault {
    
    @Override
    public int calculate(Bike bike, LocalDateTime now, Person person, BikeRental bikeRental) {
        if(now.isAfter(bike.getRentalInfos().get(bike.getRentalInfos().size()-1).getEnd())) {
            bike.addGebyr(10);
        }
        int price = bike.getGebyr()+computeHours(bike.getRentalInfos().get(0).getStart(), now)*10;
        return price;
    }

    @Override
    public void extendCalc(Bike bike) {
        bike.addGebyr(5);
    }
}
