package oving7;

public class TrainCar {

    protected int emptyWeight;
    protected int cargoWeight;
    protected int passengers;
    protected int passengerWeight;


    public TrainCar(int weight) {
        setDeadWeight(weight);
    }

    protected int getPassengerCount() {
        return passengers;
    }

    protected int getTotalWeight() {
        return emptyWeight+cargoWeight+passengerWeight;
    }

    public void setDeadWeight(int weight) {
        this.emptyWeight = weight;
    }

    public int getDeadWeight() {
        return emptyWeight;
    }

    protected void setCargoWeight(int weight) {
        this.cargoWeight = weight;
    }
    protected int getCargoWeight() {
        return cargoWeight;
    }
}
