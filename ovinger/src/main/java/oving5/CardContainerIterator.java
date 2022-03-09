package oving5;

import java.util.Iterator;

public class CardContainerIterator implements Iterator<Card> {
    
    private Iterator<Card> iter;

    public CardContainerIterator(CardContainer container) {
        this.iter = container.iterator();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Card next() {
        return iter.next();
    }
}
