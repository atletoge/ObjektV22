package oving7;

public class CargoCar extends TrainCar {


    public CargoCar(int weight, int filled) {
        super(weight);
        setCargoWeight(filled);
    }
    
    


    @Override
    public String toString() {
        return "CargoCar, vekt: "+this.getTotalWeight()+" kargovekt: "+this.getCargoWeight();
    }
}
