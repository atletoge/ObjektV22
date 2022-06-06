package Eksamener.kont19.main.java.del1;

import java.time.LocalDateTime;

public interface PricingDelegateDefault {
    public int calculate(Bike bike, LocalDateTime now, Person person, BikeRental bikeRental);

    public void extendCalc(Bike bike);
}
