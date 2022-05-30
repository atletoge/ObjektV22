package oving6.observable;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    
    private String string;
    private double tall;
    private List<StockListener> observers = new ArrayList<StockListener>();

    public Stock(String string, double tall) {
        this.string = string;
        if(tall <= 0) {
            throw new IllegalArgumentException("Aksjekursen kan ikke være null eller negativ");
        } 
        this.tall = tall;
    }

    public void setPrice(double pris) {
        if(pris <= 0) {
            throw new IllegalArgumentException("Aksjekursen kan ikke være null eller negativ");
        }
        double old = this.tall;
        this.tall = pris;
        if(old!=pris) {
            changePrice(old);
        }
    }

    public String getTicker() {
        return string;
    }

    public double getPrice() {
        return tall;
    }

    public void addStockListener(StockListener stockListener) {
        observers.add(stockListener);
    }

    public void removeStockListener(StockListener stocklistener) {
        observers.remove(stocklistener);
    }

    public void changePrice(double oldPrice) {
        for (StockListener stockListener : observers) {
            stockListener.stockPriceChanged(this, oldPrice, this.tall);
        }
    }
}
