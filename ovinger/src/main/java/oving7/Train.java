package oving7;

import java.util.ArrayList;
import java.util.List;

public class Train {
    
    private ArrayList<TrainCar> trainCars;

    public Train() {
        this.trainCars = new ArrayList<TrainCar>();
    }

    public void addTrainCar(TrainCar trainCar) {
        if(!trainCars.contains(trainCar)) {
            trainCars.add(trainCar);
        }
    }

    public boolean contains(TrainCar trainCar) {
        return trainCars.contains(trainCar);
    }

    public int getTotalWeight() {
        return trainCars.stream().mapToInt(trainCar -> trainCar.getTotalWeight()).sum();
    }

    public int getPassengerCount() {
        return trainCars.stream().mapToInt(trainCar -> trainCar.getPassengerCount()).sum();
    }

    public int getCargoWeight() {
        return trainCars.stream().mapToInt(trainCar -> trainCar.getCargoWeight()).sum();
    }

    @Override
    public String toString() {
        return trainCars.toString();
    }

    public static void main(String[] args) {
        Train train = new Train();
        System.out.println(train.getTotalWeight());
        PassengerCar passengerCar = new PassengerCar(1000, 100);
        train.addTrainCar(passengerCar);
        System.out.println(train.getTotalWeight());
        PassengerCar ps2 = new PassengerCar(1000, 100);
        train.addTrainCar(ps2);
        CargoCar cargoCar = new CargoCar(1000, 7000);
        train.addTrainCar(cargoCar);
        System.out.println(train.getTotalWeight());
        System.out.println(train);
    }
}
