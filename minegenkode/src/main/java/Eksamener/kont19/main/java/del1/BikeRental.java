package Eksamener.kont19.main.java.del1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BikeRental {
 
    // ??? 1 d)
    private Collection<Bike> bikes = new ArrayList<>();
    private Collection<GeoLocation> locations = new ArrayList<>(); //Velger lists her slik at jeg kan iterere gjennom de og sortere de alt ettersom. 
    private PricingDelegateDefault delegate = new PricingDelegate();
 
    /**
     * Counts the number of available bikes within a certain distance of a provided location.
     * @param location
     * @param distance
     * @return the number of available bikes within a certain distance of a provided location
     */
    private int countAvailableBikesNearby(GeoLocation location, double distance) {
        int available = 0;
        for (Bike bike : bikes) {
            if((location.distance(bike.getLocation()) < distance) && (bike.getRenter() != null)) {
                available++; 
            }
        }
        return available;
    }
 
    /**
     * Finds the closest station (location) within the provided (maximum) distance of the provided bike
     * @param bike
     * @param minDistance
     * @return the closest station (location) within the provided (maximum) distance of the provided bike
     */
    private GeoLocation getStationNearby(Bike bike, double maxDistance) {
        GeoLocation closest = null;
        double distance = maxDistance;
        for (GeoLocation location : locations) {
            if(location.distance(bike.getLocation()) < distance) {
                closest = location;
                distance = location.distance(bike.getLocation());
            }
        } return closest;
    }
 
    /**
     * @return the bikes that currently are rented
     */
    private Collection<Bike> getRentedBikes() {
        return bikes.stream().filter(b -> (b.getRenter()!= null)).toList();
    }
 
    /**
     * @return the bikes that are close to a station (within 30m), but still are rented
     */
    private Collection<Bike> getUnreturnedBikes() {
        return getRentedBikes().stream().filter(bike -> getStationNearby(bike, 30.0) != null).toList();

    }
    /**
     * Called when a person starts renting a bike by taking it from a station.
     * Checks the arguments before registering all necessary info of the rental.
     * @param person
     * @param now the start time of the rental
     * @param returnTime the expected return time
     * @throws (some subclass of) RuntimeException if the now isn't before returnTime
     * @throws (some subclass of) RuntimeException if the bike isn't available for rental
     */
    public void rentBike(Person person, Bike bike, LocalDateTime now, LocalDateTime returnTime) {
        if(returnTime.compareTo(now) < 0) {
            throw new IllegalArgumentException("Can't return a bike before you rent it");
        }
        if(getRentedBikes().contains(bike)){
            throw new IllegalStateException("Can't rent an already rented bike");
        }
        bike.setRenter(person);
        bike.addRentalInfo(new RentalInfo(now, returnTime));
    }
 
    /**
     * Called when a person extends an ongoing bike rental.
     * Checks the arguments before registering all necessary info of the rental extension.
     * @param person
     * @param bike
     * @param now the time the extension starts
     * @param returnTime the (new) expected return time
     * @throws (some subclass of) RuntimeException if the now isn't before returnTime
     * @throws (some subclass of) RuntimeException if the bike isn't currently being rented
     * @throws (some subclass of) RuntimeException if person isn't currently renting the bike
     */
    public void extendRental(Person person, Bike bike, LocalDateTime now, LocalDateTime returnTime) {
        if(returnTime.compareTo(now) < 0) {
            throw new IllegalArgumentException("Can't return a bike before you rent it");
        }
        if(!getRentedBikes().contains(bike)){
            throw new IllegalStateException("Bike is not rented");
        }
        if(!bike.getRenter().equals(person)) {
            throw new IllegalArgumentException("Can't extend when your not renting the bike");
        }
        if(now.isAfter(bike.getRentalInfos().get((bike.getRentalInfos().size())-1).getEnd())) {
            bike.addGebyr(10);
        }
        delegate.extendCalc(bike);
        bike.addRentalInfo(new RentalInfo(now, returnTime));
    }

    
    public void addDelegate(PricingDelegateDefault delegate) {
        this.delegate = delegate;
    }
    /**
     * Called when a person returns a bike.
     * Checks the arguments, computes the price, performs the payment and clears the rental info.
     * Note that if the payment isn't completed, the rental info should not be cleared.
     * @param person
     * @param bike
     * @param now the time the bike is returned
     * @throws (some subclass of) RuntimeException if the bike isn't currently being rented by the person argument
     * @throws (some subclass of) RuntimeException if person isn't near (within 30m of) a station
     */
    public void returnBike(Person person, Bike bike, LocalDateTime now) {
        if(!bike.getRenter().equals(person)) {
            throw new IllegalArgumentException("Can't end renting a bike you are not renting");
        } if(!getUnreturnedBikes().contains(bike)) {
            throw new IllegalArgumentException("You are not close enough to a station");
        }
        int price = delegate.calculate(bike, now, person, this);
        bike.removeRenter();
        bike.setPrice(price);
        printReceipt(person, bike);
        bike.clearRentals();
    }
    
    public int computeHours(LocalDateTime start, LocalDateTime end) {
        return (int) Duration.between(start, end).toHours() + 1;
    }
 
    /**
     * Called when the Person has returned a Bike.
     * Displays initial rent period
     * Displays extensions of the rent period
     * Computes and displays total cost, including fees for late return.
     * @param person
     * @param bike
     */
    void printReceipt(Person person, Bike bike) {
        System.out.println("Initial rental from "+bike.getRentalInfos().get(0).getStart()+" to "+bike.getRentalInfos().get(0).getEnd());
        if(bike.getRentalInfos().size() > 1) {
            int counter = 1;
            for (RentalInfo info : bike.getRentalInfos()) {
                System.out.println("Extensions "+counter+" from "+info.getStart()+" to "+.info.getEnd());
            }
        }else {
            System.out.println("No extensions");
        } System.out.println("Bike returned at "+LocalDateTime.now());
        System.out.println("Total cost: "+bike.getPrice()+" kr,-");

    }
}