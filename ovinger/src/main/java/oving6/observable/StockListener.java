package oving6.observable;

public interface StockListener {
    public void stockPriceChanged(Stock stock, double oldValue, double newValue);
}
