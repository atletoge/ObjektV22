package oving6.observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StockIndex implements StockListener {

    private String name;
    private double index;
    private List<Stock> stocks;

    @Override
    public void stockPriceChanged(Stock stock, double oldValue, double newValue) {
        this.index += newValue - oldValue;
        
    }
    
    public StockIndex(String string, Stock ...stocks) {
        this.name = string;
        this.stocks = new ArrayList<Stock>(Arrays.asList(stocks));
        this.index = 0;
        for (Stock stock : stocks) {
            stock.addStockListener(this);
            this.index += stock.getPrice();
        }
    }

    public void addStock(Stock stock) {
        if(!stocks.contains(stock)) {
            stocks.add(stock);
            stock.addStockListener(this);
            this.index += stock.getPrice();
        }
        
    }

    public void removeStock(Stock stock) {
        if(stocks.contains(stock)) {
            stocks.remove(stock);
            stock.removeStockListener(this);
            this.index -= stock.getPrice();
        }
        
    }

    public double getIndex() {
        return index;
    }

}
