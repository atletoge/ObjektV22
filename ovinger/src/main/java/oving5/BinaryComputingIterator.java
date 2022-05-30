package oving5;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class BinaryComputingIterator implements Iterator<Double> {

    private Iterator<Double> iterator1, iterator2;
    private Double default1, default2 = null;
    private BinaryOperator<Double> operator;


    public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, BinaryOperator<Double> operator) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.operator = operator;
    }

    public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, Double default1, Double default2, BinaryOperator<Double> operator) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.operator = operator;
        this.default1 = default1;
        this.default2 = default2;
    }


    @Override
    public boolean hasNext() {
        if((iterator1.hasNext() || default1 != null) && (iterator2.hasNext() || default2 != null) && (iterator1.hasNext() || iterator2.hasNext())) {
            return true;
        } return false;
    }

    @Override
    public Double next() {
        Double number1 = null;
        Double number2 = null;
        if(iterator1.hasNext()) {
            number1 = iterator1.next();
        } else {
            number1 = default1;
        }
        if(iterator2.hasNext()) {
            number2 = iterator2.next();
        }else {
            number2 = default2;
        }
        return operator.apply(number1, number2);
    }
    
}
