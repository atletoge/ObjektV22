package oving7;

public class PassengerCar extends TrainCar {


    public PassengerCar(int weight, int passengers) {
        super(weight);
        setPassengerCount(passengers);
    }


    protected void setPassengerCount(int antall) {
        this.passengers = antall;
        calculateWeight();
    }
    
    private void calculateWeight() {
        this.passengerWeight = passengers*80;
    }
    @Override
    public String toString() {
        return "PassengerCar, vekt: "+this.getTotalWeight()+" passasjerer: "+this.getPassengerCount();
    }
}
